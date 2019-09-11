package com.mpakbaz.mycvserver.common.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;


public class Captcha {

    public Captcha() {
    }

    public static String[] generateImage() {

        Properties props = new Properties();
        props.put("kaptcha.border", "no");
        props.put("kaptcha.border.color", "0,209,255");
        props.put("kaptcha.image.width", "140");
        props.put("kaptcha.image.height", "40");
        props.put("kaptcha.textproducer.font.size", "30");
        props.put("kaptcha.textproducer.font.color", "0,209,255");
        props.put("kaptcha.textproducer.char.string", "abcd2345678gfynnpwx");
        props.put("kaptcha.textproducer.char.length", "4");
        props.put("kaptcha.session.key", "code");
        props.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        props.put("kaptcha.noise.color", "white");
        props.put("kaptcha.background.clear.from", "white");
        props.put("kaptcha.background.clear.to", "white");
        props.put("kaptcha.textproducer.font.names", "??,??,????");
        props.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");

        ConfigEx configEx = new ConfigEx(props);

        List<Color> noiseColors = new ArrayList();

        noiseColors.add(new Color(8, 255, 10, 100));
        noiseColors.add(new Color(241, 180, 17, 100));
        noiseColors.add(new Color(189, 204, 221, 50));
        noiseColors.add(new Color(204, 255, 0, 50));

        configEx.setNoiseColors(noiseColors.toArray(new Color[0]));

        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(configEx);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String capText = captchaProducer.createText();
        try {
            BufferedImage bi = captchaProducer.createImage(capText);
            ImageIO.write(bi, "png", bout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new String[]{capText, new String(Base64.getEncoder().encode(bout.toByteArray()))};

    }


    public static String generateText() {
        return new StringTokenizer(UUID.randomUUID().toString(), "-").nextToken();
    }
}