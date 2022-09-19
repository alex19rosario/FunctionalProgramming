(ns com.mycompany.functionalprogramming.exercise2-1.BlockStructure)

;;USING BLOCK STRUCTURE AND LEXICAL SCOPING WHICH ARE EXPLAINED IN PAGE 36
(defn sqrt [x]
  (defn abs [a]
    (if (< a 0) (- a) a))
  (defn avg [x, y] (/ (+ x y) 2))
  (defn improve [guess] (avg guess (/ x guess)))
  (defn good_enough? [guess] (< (abs (- guess (improve guess))) (* guess 0.0001)))
  (defn sqrt-iter [guess]
    (if (good_enough? guess)
      guess
      (sqrt-iter (improve guess))))
  (sqrt-iter 1.0)
  )

(defn cube_root [x]
  (defn abs [a]
    (if (< a 0) (- a) a))
  (defn improve [guess] (/ (+ (/ x (sqr guess)) (* 2 guess)) 3))
  (defn good_enough? [guess] (< (abs (- guess (improve guess))) (* guess 0.0001)))
  (defn sqrt-iter [guess]
    (if (good_enough? guess)
      guess
      (sqrt-iter (improve guess))))
  (sqrt-iter 1.0)
  )
