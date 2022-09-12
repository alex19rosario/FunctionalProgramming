(ns com.mycompany.functionalprogramming.exercise2-1.Exercises)

;;THIS FUNCTION RETURNS THE GREATEST BETWEEN THREE NUMBERS
(defn greatest [x, y, z] (cond
                           (and (> x y) (> x z)) x
                           (and (> y x) (> y z)) y
                           :else z))

;;THIS FUNCTION RETURNS THE SECOND GREATEST BETWEEN THREE NUMBERS
(defn second_greatest [x, y, z] (cond
                                  (or (and (> x y) (< x z)) (and (> x z) (< x y))) x
                                  (or (and (> y x) (< y z)) (and (> y z) (< y x))) y
                                  :else z))



;;=====================SQUARE ROOT OF A NUMBER BY NEWTON'S METHOD============================

;;THIS FUNCTION RETURNS THE ABSOLUTE VALUE
(defn abs [x] (cond
                (< x 0) (- x)
                (= x 0) 0
                (> x 0) x))

;;THIS FUNCTION RETURNS THE SQUARE OF A NUMBER
(defn sqr [a] (* a a))

;;THIS FUNCTION RETURNS A BOOLEAN THAT CLARIFY WHETHER THE GUESS IS GOOD ENOUGH
(defn good-enough? [guess, x] (< (abs (- (sqr guess) x)) 0.0001))

;;THIS FUNCTION IS TO CALCULATE THE AVERAGE BETWEEN TWO NUMBERS
(defn avg [x, y] (/ (+ x y) 2))

;;THIS FUNCTION IMPROVE THE GUESS BY AVERAGING
(defn improve-guess [guess x] (avg guess (/ x guess)))

;;THIS FUNCTION RETURNS THE SQUARE BY GUESSING AND IMPROVING
(defn sqrt-iter [guess, x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve-guess guess x) x)))

;;THIS FUNCTION RETURNS THE SQUARE, BUT JUST ASKING FOR THE NUMBER
(defn sqrt [x] (sqrt-iter 1 x))

;;===========================================================================================

;;THIS FUNCTION RETURNS THE SUM OF THE SQUARE OF THE LARGEST NUMBERS BETWEEN THREE NUMBERS
(defn sumSqrOfTwoGreatest [x, y, z] (+ (sqr (greatest x, y, z)) (sqr (second_greatest x, y, z))))