package com.mpakbaz.mycvserver.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

@SuppressWarnings("nls")
public class MimeTypes {
    private static final HashMap<String, String> mime_Types = new HashMap<>();
    private static final Map<String, String> mimeTypes = new HashMap<String, String>();

    static {
        mime_Types.put("html", "text/html");
        mime_Types.put("htm", "text/html");
        mime_Types.put("shtml", "text/html");
        mime_Types.put("css", "text/css");
        mime_Types.put("xml", "text/xml");
        mime_Types.put("gif", "image/gif");
        mime_Types.put("jpeg", "image/jpeg");
        mime_Types.put("jpg", "image/jpeg");
        mime_Types.put("js", "application/javascript");
        mime_Types.put("atom", "application/atom+xml");
        mime_Types.put("rss", "application/rss+xml");
        mime_Types.put("mml", "text/mathml");
        mime_Types.put("txt", "text/plain");
        mime_Types.put("jad", "text/vnd.sun.j2me.app-descriptor");
        mime_Types.put("wml", "text/vnd.wap.wml");
        mime_Types.put("htc", "text/x-component");
        mime_Types.put("png", "image/png");
        mime_Types.put("tif", "image/tiff");
        mime_Types.put("tiff", "image/tiff");
        mime_Types.put("wbmp", "image/vnd.wap.wbmp");
        mime_Types.put("ico", "image/x-icon");
        mime_Types.put("jng", "image/x-jng");
        mime_Types.put("bmp", "image/x-ms-bmp");
        mime_Types.put("svg", "image/svg+xml");
        mime_Types.put("svgz", "image/svg+xml");
        mime_Types.put("webp", "image/webp");
        mime_Types.put("woff", "application/font-woff");
        mime_Types.put("jar", "application/java-archive");
        mime_Types.put("war", "application/java-archive");
        mime_Types.put("ear", "application/java-archive");
        mime_Types.put("json", "application/json");
        mime_Types.put("hqx", "application/mac-binhex40");
        mime_Types.put("doc", "application/msword");
        mime_Types.put("pdf", "application/pdf");
        mime_Types.put("ps", "application/postscript");
        mime_Types.put("eps", "application/postscript");
        mime_Types.put("ai", "application/postscript");
        mime_Types.put("rtf", "application/rtf");
        mime_Types.put("m3u8", "application/vnd.apple.mpegurl");
        mime_Types.put("xls", "application/vnd.ms-excel");
        mime_Types.put("eot", "application/vnd.ms-fontobject");
        mime_Types.put("ppt", "application/vnd.ms-powerpoint");
        mime_Types.put("wmlc", "application/vnd.wap.wmlc");
        mime_Types.put("kml", "application/vnd.google-earth.kml+xml");
        mime_Types.put("kmz", "application/vnd.google-earth.kmz");
        mime_Types.put("7z", "application/x-7z-compressed");
        mime_Types.put("cco", "application/x-cocoa");
        mime_Types.put("jardiff", "application/x-java-archive-diff");
        mime_Types.put("jnlp", "application/x-java-jnlp-file");
        mime_Types.put("run", "application/x-makeself");
        mime_Types.put("pl", "application/x-perl");
        mime_Types.put("pm", "application/x-perl");
        mime_Types.put("prc", "application/x-pilot");
        mime_Types.put("pdb", "application/x-pilot");
        mime_Types.put("rar", "application/x-rar-compressed");
        mime_Types.put("rpm", "application/x-redhat-package-manager");
        mime_Types.put("sea", "application/x-sea");
        mime_Types.put("swf", "application/x-shockwave-flash");
        mime_Types.put("sit", "application/x-stuffit");
        mime_Types.put("tcl", "application/x-tcl");
        mime_Types.put("tk", "application/x-tcl");
        mime_Types.put("der", "application/x-x509-ca-cert");
        mime_Types.put("pem", "application/x-x509-ca-cert");
        mime_Types.put("crt", "application/x-x509-ca-cert");
        mime_Types.put("xpi", "application/x-xpinstall");
        mime_Types.put("xhtml", "application/xhtml+xml");
        mime_Types.put("xspf", "application/xspf+xml");
        mime_Types.put("zip", "application/zip");
        mime_Types.put("bin", "application/octet-stream");
        mime_Types.put("exe", "application/octet-stream");
        mime_Types.put("dll", "application/octet-stream");
        mime_Types.put("deb", "application/octet-stream");
//        mime_Types.put("dmg", "application/octet-stream");
//        mime_Types.put("iso", "application/octet-stream");
        mime_Types.put("img", "application/octet-stream");
        mime_Types.put("msi", "application/octet-stream");
        mime_Types.put("msp", "application/octet-stream");
        mime_Types.put("msm", "application/octet-stream");
        mime_Types.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        mime_Types.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mime_Types.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        mime_Types.put("mid", "audio/midi");
//        mime_Types.put("midi", "audio/midi");
        mime_Types.put("kar", "audio/midi");
        mime_Types.put("mp3", "audio/mpeg");
        mime_Types.put("ogg", "audio/ogg");
//        mime_Types.put("m4a", "audio/x-m4a");
//        mime_Types.put("ra", "audio/x-realaudio");
        mime_Types.put("3gpp", "video/3gpp");
        mime_Types.put("3gp", "video/3gpp");
//        mime_Types.put("ts", "video/mp2t");
        mime_Types.put("mp4", "video/mp4");
        mime_Types.put("mpeg", "video/mpeg");
        mime_Types.put("mpg", "video/mpeg");
        mime_Types.put("mov", "video/quicktime");
        mime_Types.put("webm", "video/webm");
//        mime_Types.put("flv", "video/x-flv");
//        mime_Types.put("m4v", "video/x-m4v");
//        mime_Types.put("mng", "video/x-mng");
//        mime_Types.put("asx", "video/x-ms-asf");
//        mime_Types.put("asf", "video/x-ms-asf");
        mime_Types.put("wmv", "video/x-ms-wmv");
        mime_Types.put("avi", "video/x-msvideo");
    }

    public static String getExtension(String mimeTypeFile) {
        return mime_Types.entrySet().stream().filter(e -> e.getValue() == mimeTypeFile).findFirst().get().getKey();
    }

    public static String getContentType(byte[] data) {
        return getContentType(data, null);
    }

    public static String getContentType(byte[] data, String name) {
        if (data == null) {
            return null;
        }
        byte[] header = new byte[11];
        System.arraycopy(data, 0, header, 0, Math.min(data.length, header.length));
        int c1 = header[0] & 0xff;
        int c2 = header[1] & 0xff;
        int c3 = header[2] & 0xff;
        int c4 = header[3] & 0xff;
        int c5 = header[4] & 0xff;
        int c6 = header[5] & 0xff;
        int c7 = header[6] & 0xff;
        int c8 = header[7] & 0xff;
        int c9 = header[8] & 0xff;
        int c10 = header[9] & 0xff;
        int c11 = header[10] & 0xff;

//        if (c1 == 0xCA && c2 == 0xFE && c3 == 0xBA && c4 == 0xBE) {
//            return "application/java-vm";
//        }

        if (c1 == 0xD0 && c2 == 0xCF && c3 == 0x11 && c4 == 0xE0 && c5 == 0xA1 && c6 == 0xB1 && c7 == 0x1A && c8 == 0xE1) {
            // if the name is set then check if it can be validated by name, because it could be a xls or powerpoint
            String contentType = guessContentTypeFromName(name);
            if (contentType != null) {
                return contentType;
            }
            return "application/msword";
        }
        if (c1 == 0x25 && c2 == 0x50 && c3 == 0x44 && c4 == 0x46 && c5 == 0x2d && c6 == 0x31 && c7 == 0x2e) {
            return "application/pdf";
        }

        if (c1 == 0x38 && c2 == 0x42 && c3 == 0x50 && c4 == 0x53 && c5 == 0x00 && c6 == 0x01) {
            return "image/photoshop";
        }

//        if (c1 == 0x25 && c2 == 0x21 && c3 == 0x50 && c4 == 0x53) {
//            return "application/postscript";
//        }

        if (c1 == 0xff && c2 == 0xfb && c3 == 0x30) {
            return "audio/mp3";
        }

        if (c1 == 0x49 && c2 == 0x44 && c3 == 0x33) {
            return "audio/mp3";
        }

//        if (c1 == 0xAC && c2 == 0xED) {
//            // next two bytes are version number, currently 0x00 0x05
//            return "application/x-java-serialized-object";
//        }

        if (c1 == '<') {
            if (c2 == '!' ||
                    ((c2 == 'h' && (c3 == 't' && c4 == 'm' && c5 == 'l' || c3 == 'e' && c4 == 'a' && c5 == 'd')
                            || (c2 == 'b' && c3 == 'o' && c4 == 'd' && c5 == 'y')))
                    ||
                    ((c2 == 'H' && (c3 == 'T' && c4 == 'M' && c5 == 'L' || c3 == 'E' && c4 == 'A' && c5 == 'D')
                            || (c2 == 'B' && c3 == 'O' && c4 == 'D' && c5 == 'Y')))) {
                return "text/html";
            }

            if (c2 == '?' && c3 == 'x' && c4 == 'm' && c5 == 'l' && c6 == ' ') {
                return "application/xml";
            }
        }

        // big and little endian UTF-16 encodings, with byte order mark
        if (c1 == 0xfe && c2 == 0xff) {
            if (c3 == 0 && c4 == '<' && c5 == 0 && c6 == '?' && c7 == 0 && c8 == 'x') {
                return "application/xml";
            }
        }

        if (c1 == 0xff && c2 == 0xfe) {
            if (c3 == '<' && c4 == 0 && c5 == '?' && c6 == 0 && c7 == 'x' && c8 == 0) {
                return "application/xml";
            }
        }

        if (c1 == 'B' && c2 == 'M') {
            return "image/bmp";
        }

        if (c1 == 0x49 && c2 == 0x49 && c3 == 0x2a && c4 == 0x00) {
            return "image/tiff";
        }

        if (c1 == 0x4D && c2 == 0x4D && c3 == 0x00 && c4 == 0x2a) {
            return "image/tiff";
        }

        if (c1 == 'G' && c2 == 'I' && c3 == 'F' && c4 == '8') {
            return "image/gif";
        }

        if (c1 == '#' && c2 == 'd' && c3 == 'e' && c4 == 'f') {
            return "image/x-bitmap";
        }

        if (c1 == '!' && c2 == ' ' && c3 == 'X' && c4 == 'P' && c5 == 'M' && c6 == '2') {
            return "image/x-pixmap";
        }

        if (c1 == 137 && c2 == 80 && c3 == 78 && c4 == 71 && c5 == 13 && c6 == 10 && c7 == 26 && c8 == 10) {
            return "image/png";
        }

        if (c1 == 0xFF && c2 == 0xD8 && c3 == 0xFF) {
            if (c4 == 0xE0) {
                return "image/jpeg";
            }

            /**
             * File format used by digital cameras to store images. Exif Format can be read by any application supporting JPEG. Exif Spec can be found at:
             * http://www.pima.net/standards/it10/PIMA15740/Exif_2-1.PDF
             */
            if ((c4 == 0xE1) && (c7 == 'E' && c8 == 'x' && c9 == 'i' && c10 == 'f' && c11 == 0)) {
                return "image/jpeg";
            }

            if (c4 == 0xEE) {
                return "image/jpg";
            }
        }

        /**
         * According to http://www.opendesign.com/files/guestdownloads/OpenDesign_Specification_for_.dwg_files.pdf first 6 bytes are of type "AC1018" (for
         * example) and the next 5 bytes are 0x00.
         */
        if ((c1 == 0x41 && c2 == 0x43) && (c7 == 0x00 && c8 == 0x00 && c9 == 0x00 && c10 == 0x00 && c11 == 0x00)) {
            return "application/acad";
        }

        if (c1 == 0x2E && c2 == 0x73 && c3 == 0x6E && c4 == 0x64) {
            return "audio/basic"; // .au
            // format,
            // big
            // endian
        }

        if (c1 == 0x64 && c2 == 0x6E && c3 == 0x73 && c4 == 0x2E) {
            return "audio/basic"; // .au
            // format,
            // little
            // endian
        }

        if (c1 == 'R' && c2 == 'I' && c3 == 'F' && c4 == 'F') {
            /*
             * I don't know if this is official but evidence suggests that .wav files start with "RIFF" - brown
             */
            return "audio/x-wav";
        }

//        if (c1 == 'P' && c2 == 'K') {
//            // its application/zip but this could be a open office thing if name is given
//            String contentType = guessContentTypeFromName(name);
//            if (contentType != null) {
//                return contentType;
//            }
//            return "application/zip";
//        }
        return guessContentTypeFromName(name);
    }


    public static String guessContentTypeFromName(String name) {
        if (name == null)
            return null;

        int lastIndex = name.lastIndexOf('.');
        if (lastIndex != -1) {
            String extention = name.substring(lastIndex + 1).toLowerCase();
            if (mimeTypes.size() == 0) {
                HashMap<String, String> tempMap = new HashMap<String, String>();
                InputStream is = MimeTypes.class.getResourceAsStream("mime.types.properties");
                try {
                    Properties properties = new Properties();
                    properties.load(is);
                    for (Object key : properties.keySet()) {
                        String property = properties.getProperty((String) key);
                        StringTokenizer st = new StringTokenizer(property, " ");
                        while (st.hasMoreTokens()) {
                            tempMap.put(st.nextToken(), (String) key);
                        }
                    }
                } catch (IOException e) {
                    // Debug.error(e);
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // Debug.error(e);
                    }
                }
                synchronized (mimeTypes) {
                    mimeTypes.putAll(tempMap);
                }
            }
            return mimeTypes.get(extention);
        }
        return null;
    }
}
