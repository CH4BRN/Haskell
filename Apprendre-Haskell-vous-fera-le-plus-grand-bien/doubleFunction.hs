doubleMe x = x + x

doubleUs x y = (x+x)+(y+y)

secondDoubleUs x y = (doubleMe x)+(doubleMe y)

doubleSmallNumber x =   
    if x > 100
    then x
    else doubleMe x

doubleSmallNumberAndAddOne x =
    (
        if x > 100
        then x
        else doubleMe x
    )
    +1
