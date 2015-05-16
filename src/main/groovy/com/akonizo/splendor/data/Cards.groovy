package com.akonizo.splendor.data

class Cards {

    static final String WHITE = 'WHITE'
    static final String BLUE = 'BLUE'
    static final String GREEN = 'GREEN'
    static final String RED = 'RED'
    static final String BLACK = 'BLACK'
    static final String GOLD = 'GOLD'
    
    Set<Card> cards = new HashSet<Card>()
    
    Cards() {
        init()
    }
    
    def init() {
        add BLUE, BLUE, 'WWWWWWWBBB', 5
        add BLUE, BLUE, 'WWWWWWW', 4
        add BLUE, BLUE, 'WWWWWWBBBKKK', 4
        add BLUE, BLUE, 'WWWGGGRRRKKKKK', 3
        add BLUE, GREEN, 'BBBBBBBGGG', 5
        add BLUE, GREEN, 'BBBBBBB', 4
        add BLUE, GREEN, 'WWWBBBBBBGGG', 4
        add BLUE, GREEN, 'WWWWWBBBRRRKKK', 3
        add BLUE, RED, 'GGGGGGGRRR', 5
        add BLUE, RED, 'GGGGGGG', 4
        add BLUE, RED, 'BBBGGGGGGRRR', 4
        add BLUE, RED, 'WWWBBBBBGGGKKK', 3
        add BLUE, BLACK, 'RRRRRRRKKK', 5
        add BLUE, BLACK, 'RRRRRRR', 4
        add BLUE, BLACK, 'GGGRRRRRRKKK', 4
        add BLUE, BLACK, 'WWWBBBGGGGGRRR', 3
        add BLUE, WHITE, 'WWWKKKKKKK', 5
        add BLUE, WHITE, 'KKKKKKK', 4
        add BLUE, WHITE, 'WWWRRRKKKKKK', 4
        add BLUE, WHITE, 'BBBGGGRRRRRKKK', 3
        
        add GOLD, BLACK, 'WWWGGGKK', 1
        add GOLD, RED, 'BBBRRKKK', 1
        add GOLD, GREEN, 'WWWGGRRR', 1
        add GOLD, BLUE, 'BBGGGKKK', 1
        add GOLD, WHITE, 'WWBBBRRR', 1
        add GOLD, RED, 'WWRRKKK', 1
        add GOLD, BLUE, 'BBGGRRR', 1
        add GOLD, WHITE, 'GGGRRKK', 1
        add GOLD, GREEN, 'WWBBBKK', 1
        add GOLD, BLACK, 'WWWBBGG', 1
        add GOLD, BLUE, 'WWRKKKK', 2
        
    }
    
    def add( String r, String p, String cost, int value=0 ) {
        
    }
}

class Card {}

enum Rank {
    GREEN, GOLD, BLUE
}

enum Color {
    WHITE( 'W' ),
    BLUE( 'B' ),
    GREEN( 'G' ),
    RED( 'R' ),
    BLACK( 'K' )
    
    private final char abbrev
    
    Color( char a ) {
        this.abbrev = a
    }
}