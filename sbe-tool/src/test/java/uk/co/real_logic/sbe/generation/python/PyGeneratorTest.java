package uk.co.real_logic.sbe.generation.python;

import org.agrona.generation.StringWriterOutputManager;
import org.junit.Test;
import uk.co.real_logic.sbe.TestUtil;
import uk.co.real_logic.sbe.ir.Ir;
import uk.co.real_logic.sbe.xml.IrGenerator;
import uk.co.real_logic.sbe.xml.MessageSchema;
import uk.co.real_logic.sbe.xml.ParserOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static uk.co.real_logic.sbe.xml.XmlSchemaParser.parse;

public class PyGeneratorTest {
    @Test()
    public void codeGenerationSchemaTest() throws Exception {
        final ParserOptions options = ParserOptions.builder().stopOnError(true).build();
        final MessageSchema schema = parse(TestUtil.getLocalResource("example-schema.xml"), options);
        final IrGenerator irg = new IrGenerator();
        final Ir ir = irg.generate(schema);
        final StringWriterOutputManager outputManager = new StringWriterOutputManager();
        final PyGenerator generator = new PyGenerator(ir, outputManager);
        generator.generate();
        assertEquals(13, outputManager.getSources().size());
        assertTrue(outputManager.getSources().containsKey("null.engine"));
        assertTrue(outputManager.getSources().containsKey("null.optional_extras"));
        assertTrue(outputManager.getSources().containsKey("null.booster"));
        assertTrue(outputManager.getSources().containsKey("null.message_header"));
        assertTrue(outputManager.getSources().containsKey("null.boolean_type"));
        assertTrue(outputManager.getSources().containsKey("null.model"));
        assertTrue(outputManager.getSources().containsKey("null.var_string_encoding"));
        assertTrue(outputManager.getSources().containsKey("null.boost_type"));
        assertTrue(outputManager.getSources().containsKey("null._struct_defs"));
        assertTrue(outputManager.getSources().containsKey("null.group_size_encoding"));
        assertTrue(outputManager.getSources().containsKey("null.meta_attribute"));
        assertTrue(outputManager.getSources().containsKey("null.__init__"));
    }
}
