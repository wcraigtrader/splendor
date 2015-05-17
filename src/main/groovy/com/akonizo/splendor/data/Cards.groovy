package com.akonizo.splendor.data

import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import com.xlson.groovycsv.CsvParser

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.util.logging.Slf4j

@Slf4j
class Cards {

    Set<Card> cards = new HashSet<Card>()

    void load(String data) {
        load( new StringReader( data ) )
    }

    void load(Reader stream) {
        log.info( "Clearing stored cards" )
        cards.clear()

        for ( row in CsvParser.parseCsv(stream) ) {
            def card = new Card( row.deck, row.produces, row.requires, row.value )
            log.info( "Add ${card}" )
            cards.add( card )
        }
    }
    
    static Cards getAll() {
        Cards all = new Cards()
        all.load(new InputStreamReader(Cards.class.classLoader.getResourceAsStream('cards.csv')))
        return all
    }
}
