# Generic UIMA pipeline

Simple UIMA pipeline wrap as a console application annotating Sentences, Items and Events. The last ones are easily customisable by updating the current list of terms.

Generated annotations:
 - Sentences
    - Annotation
        - de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence
    - Annotator
        - OpenNlpSegmenter: https://dkpro.github.io/dkpro-core/releases/1.9.0/docs/component-reference.html#engine-OpenNlpSegmenter
 - Items
    - Annotation
        - genericpipeline.Item
            - property: 'value'(String)
    - Annotator
        - UIMA RUTA MARKTABLE
        - Resources
            - word table taken from: ruta_resources/item_table.csv
                - The first column contains a list of unique items to be tagged in the text.
                - The second column value is assigned to the property 'value'
 - Events
    - Annotation
        - genericpipeline.Event
    - Annotator: 
        - UIMA RUTA MARKFAST
        - Resources
            - word list taken from: ruta_resources/event_list.txt

## Usage

1. Download and extract the application: https://github.com/ewoij/generic-uima-pipeline/releases/tag/v1.0.0
2. Update if necessary the following files with your own domain terms:
    - ruta_resources/item_table.csv
    - ruta_resources/event_list.txt
2. Run from the current directory
    ```
    java -Xms4000m -jar generic-uima-pipeline-1.0.0-standalone.jar <input folder containing text files> <output folder>
    ```

## Resources
 - item_table.csv was built from a subset of [NCI-Thesaurus](https://github.com/NCI-Thesaurus/thesaurus-obo-edition)