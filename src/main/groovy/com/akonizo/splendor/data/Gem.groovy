package com.akonizo.splendor.data

/** Enumerate the colors of Gems */
enum Gem {
    WHITE( 'W' ),
    BLUE( 'B' ),
    GREEN( 'G' ),
    RED( 'R' ),
    BLACK( 'K' )

    /** Abbreviation character for each Gem color */
    final char abbrev

    /** Map abbreviations to Gems */
    static final Map abbreviations

    static {
        abbreviations = [:] as HashMap
        values().each { color ->
            abbreviations.put( (Character) color.abbrev, color )
        }
    }

    /** Construct a Gem */
    Gem ( String a ) {
        this.abbrev = a[0]
    }

    /** Look up a Gem, by String */
    static get( String id ) {
        return get( (char) id[0] )
    }

    /** Look up a Gem, by character */
    static get( Character id ) {
        return abbreviations[ id.toUpperCase() ]
    }
}
