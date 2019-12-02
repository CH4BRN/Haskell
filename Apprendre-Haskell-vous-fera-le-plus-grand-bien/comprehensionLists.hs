[x*2 | x <- [1..10]]

[x*2 | x <- [1..10], x*2 >= 12]

[x+x | x <- take 10 ([0..])]

[ x | x <- [50..100], x `mod` 7 == 3]
boomBangs xs = [if x < 10 then "BOOM!" else "BANG!" | x <- xs , odd x ]

[ x | x <- [10..20], x /= 13, x /= 15, x /= 19]