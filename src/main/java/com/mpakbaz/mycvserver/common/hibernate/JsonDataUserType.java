package com.mpakbaz.mycvserver.common.hibernate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonDataUserType implements UserType {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Override
    public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (Objects.isNull(value)) {
            ps.setNull(index, Types.OTHER);
            return;
        }
//        else
//            ps.setObject(index, gson.toJson(value, Map.class), Types.OTHER);

        try {
            final ObjectMapper mapper = new ObjectMapper();
            final StringWriter w = new StringWriter();
            mapper.writeValue(w, value);
            w.flush();
            ps.setObject(index, w.toString(), Types.OTHER);
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert Invoice to String: " + ex.getMessage(), ex);
        }

    }

//    @Override
//    public Object deepCopy(Object originalValue) throws HibernateException {
//        if (originalValue == null)
//            return null;
//
//
//        if (!(originalValue instanceof Map))
//            return null;
//
//
//        Map<String, String> resultMap = new HashMap<>();
//
//        Map<?, ?> tempMap = (Map<?, ?>) originalValue;
//        tempMap.forEach((key, value) -> resultMap.put((String) key, (String) value));
//
//        return resultMap;
//    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) {
            return null;
        }

//        if (!(value instanceof Map)) {
//            return null;
//        }

        try {
            // use serialization to create a deep copy
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            bos.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(inputStream).readObject();
        } catch (ClassNotFoundException | IOException ex) {
            throw new HibernateException(ex);
        }
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        final String cellContent = resultSet.getString(names[0]);
        if (resultSet.wasNull())
            return null;

        if (resultSet.getArray(names[0]) == null)
            return new Integer[0];


        try {
            final ObjectMapper mapper = new ObjectMapper();


            return mapper.readValue(cellContent.getBytes("UTF-8"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert String to Invoice: " + ex.getMessage(), ex);
        }

    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object copy = deepCopy(value);

        if (copy instanceof Serializable) {
            return (Serializable) copy;
        }

        throw new SerializationException(String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
    }


    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }

        return x.hashCode();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }

    @Override
    public Class<?> returnedClass() {
        return Map.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

}
