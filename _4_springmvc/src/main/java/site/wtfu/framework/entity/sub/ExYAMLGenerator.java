package site.wtfu.framework.entity.sub;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.util.StringQuotingChecker;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.events.MappingStartEvent;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/5
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ExYAMLGenerator extends YAMLGenerator {
    public ExYAMLGenerator(IOContext ctxt, int jsonFeatures, int yamlFeatures, StringQuotingChecker quotingChecker, ObjectCodec codec, Writer out, DumperOptions.Version version) throws IOException {
        super(ctxt, jsonFeatures, yamlFeatures, quotingChecker, codec, out, version);
    }

    public ExYAMLGenerator(IOContext ctxt, int jsonFeatures, int yamlFeatures, StringQuotingChecker quotingChecker, ObjectCodec codec, Writer out, DumperOptions dumperOptions) throws IOException {
        super(ctxt, jsonFeatures, yamlFeatures, quotingChecker, codec, out, dumperOptions);
    }

    public ExYAMLGenerator(IOContext ctxt, int jsonFeatures, int yamlFeatures, ObjectCodec codec, Writer out, DumperOptions.Version version) throws IOException {
        super(ctxt, jsonFeatures, yamlFeatures, codec, out, version);
    }

    public  void writeStartCObject() throws IOException
    {
        _verifyValueWrite("start an object");
        _writeContext = _writeContext.createChildObjectContext();
        DumperOptions.FlowStyle style = DumperOptions.FlowStyle.FLOW;
        String yamlTag = _typeId;
        boolean implicit = (yamlTag == null);
        String anchor = _objectId;
        if (anchor != null) {
            _objectId = null;
        }
        _emit(new MappingStartEvent(anchor, yamlTag,
                implicit, null, null, style));
    }

    public void writeQuotedString(String text) throws IOException, JsonGenerationException
    {
        if (text == null) {
            writeNull();
            return;
        }
        /*if (_writeContext.writeFieldName(text) == JsonWriteContext.STATUS_EXPECT_VALUE) {
            _reportError("Can not write a field name, expecting a value");
        }*/
        _verifyValueWrite("write String value");

        // [dataformats-text#50]: Empty String always quoted
        if (text.isEmpty()) {
            _writeScalar(text, "string", DumperOptions.ScalarStyle.DOUBLE_QUOTED);
            return;
        }
        DumperOptions.ScalarStyle style = DumperOptions.ScalarStyle.DOUBLE_QUOTED;
        _writeScalar(text, "string", style);
    }
}
