package com.akonizo.splendor.data

enum Deck {
    GREEN( 0x33ff99 ), 
    GOLD(  0xffff66 ), 
    BLUE(  0x3399ff )
    
    final int color
    
    Deck( int c ) {
        this.color = c
    }
}
