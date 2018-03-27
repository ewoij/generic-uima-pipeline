package genericpipeline;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import java.io.File;
import java.io.IOException;
import org.apache.uima.UIMAException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.RutaEngine;

import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;

public class App
{
    public static void main(String[] args) throws ResourceInitializationException, UIMAException, IOException
    {
    	String inputDir = args[0];
    	String inputFilesFilter = inputDir + File.separator + "*.txt";
    	
    	String outputDir = args[1];
    	
    	String rutaResourcesDir = "ruta_resources";
    	
        runPipeline(
    		createReader(TextReader.class,
				TextReader.PARAM_SOURCE_LOCATION, inputFilesFilter,
				TextReader.PARAM_LANGUAGE, "en"),
    		createEngine(OpenNlpSegmenter.class),
    		createEngine(RutaEngine.class,
				RutaEngine.PARAM_MAIN_SCRIPT, "ruta.genericpipeline.MarkItems",
				RutaEngine.PARAM_RESOURCE_PATHS, rutaResourcesDir),
    		createEngine(RutaEngine.class,
				RutaEngine.PARAM_MAIN_SCRIPT, "ruta.genericpipeline.MarkEvents",
				RutaEngine.PARAM_RESOURCE_PATHS, rutaResourcesDir),
    		createEngine(XmiWriter.class,
				XmiWriter.PARAM_OVERWRITE, true, 
				XmiWriter.PARAM_TARGET_LOCATION, outputDir));
    }
}
