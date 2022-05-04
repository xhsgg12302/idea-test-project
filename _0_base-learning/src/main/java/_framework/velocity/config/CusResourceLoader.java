package _framework.velocity.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ClassUtils;
import org.apache.velocity.util.ExtProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Vector;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-04
 */
public class CusResourceLoader extends ResourceLoader {

    private String path;

    @Override
    public void init(ExtProperties extProperties) {
        Vector path = extProperties.getVector("path");
        this.path = extProperties.getString("path");
        this.log.trace("CusResourceLoader: initialization complete.");
    }

    @Override
    public Reader getResourceReader(String name, String encoding) throws ResourceNotFoundException {
        Reader result = null;
        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("No template name provided");
        } else {
            InputStream rawStream = null;

            try {
                rawStream = getResourceAsStream(this.getClass(), path,name);
                if (rawStream != null) {
                    result = this.buildReader(rawStream, encoding);
                }
            } catch (Exception var8) {
                if (rawStream != null) {
                    try {
                        rawStream.close();
                    } catch (IOException var7) {
                    }
                }

                throw new ResourceNotFoundException("CusResourceLoader problem with template: " + name, var8);
            }

            if (result == null) {
                String msg = "CusResourceLoader Error: cannot find resource " + name;
                throw new ResourceNotFoundException(msg);
            } else {
                return result;
            }
        }
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        return false;
    }

    @Override
    public long getLastModified(Resource resource) {
        return 0;
    }

    public static InputStream getResourceAsStream(Class claz, String path,String name) {
        InputStream result;
        for(result = null; name.startsWith("/"); name = name.substring(1)) {
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = claz.getClassLoader();
            result = classLoader.getResourceAsStream(path + File.separator + name);
        } else {
            result = classLoader.getResourceAsStream(path + File.separator + name);
            if (result == null) {
                classLoader = claz.getClassLoader();
                if (classLoader != null) {
                    result = classLoader.getResourceAsStream(name);
                }
            }
        }

        return result;
    }
}
