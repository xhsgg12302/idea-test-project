package site.wtfu.framework.entity.sub;

import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.yaml.snakeyaml.DumperOptions;

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
public class ExYAMLFactory extends YAMLFactory {

    protected YAMLGenerator _createGenerator(Writer out, IOContext ctxt) throws IOException {

        int feats = _yamlGeneratorFeatures;
        DumperOptions dumperOptions = buildDumperOptions(_generatorFeatures, feats, _version);


        return new ExYAMLGenerator(ctxt, _generatorFeatures, feats,
                _quotingChecker, _objectCodec, out, dumperOptions);

    }

    protected DumperOptions buildDumperOptions(int jsonFeatures, int _formatFeatures,
                                               org.yaml.snakeyaml.DumperOptions.Version version)
    {
        DumperOptions opt = new DumperOptions();
        // would we want canonical?
        if (YAMLGenerator.Feature.CANONICAL_OUTPUT.enabledIn(_formatFeatures)) {
            opt.setCanonical(true);
        } else {
            opt.setCanonical(false);
            // if not, MUST specify flow styles
            opt.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        }
        // split-lines for text blocks?
        opt.setSplitLines(YAMLGenerator.Feature.SPLIT_LINES.enabledIn(_formatFeatures));
        // array indentation?
        if (YAMLGenerator.Feature.INDENT_ARRAYS.enabledIn(_formatFeatures)) {
            // But, wrt [dataformats-text#34]: need to set both to diff values to work around bug
            // (otherwise indentation level is "invisible". Note that this should NOT be necessary
            // but is needed up to at least SnakeYAML 1.18.
            // Also looks like all kinds of values do work, except for both being 2... weird.
            opt.setIndicatorIndent(1);
            opt.setIndent(2);
        }
        // [dataformats-text#175]: further configurability that overrides prev setting
        if (YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR.enabledIn(_formatFeatures)) {
            opt.setIndicatorIndent(2);
            opt.setIndentWithIndicator(true);
        }
        // 14-May-2018: [dataformats-text#84] allow use of platform linefeed
        if (YAMLGenerator.Feature.USE_PLATFORM_LINE_BREAKS.enabledIn(_formatFeatures)) {
            opt.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
        }

        if (YAMLGenerator.Feature.ALLOW_LONG_KEYS.enabledIn(_formatFeatures)) {
            opt.setMaxSimpleKeyLength(1024);
        }
        return opt;
    }
}
