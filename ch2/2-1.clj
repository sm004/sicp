(defn numer [x] (first x))
(defn denom [x] (second x))

(defn abs [x] (if (< x 0) (- x) x))

(defn gcd [a b]
  (if (= b 0)
      a
      (gcd b (rem a b))))

(defn make-rat [n d]
  ((fn [x y]
     (let [g (abs (gcd x y))]
       [(quot x g) (quot y g)]))
   (if (< d 0) (- n) n)
   (abs d)))

(defn print-rat [x]
  (print (numer x))
  (print "/")
  (print (denom x))
  (println))

(print-rat (make-rat -2 -4))
(print-rat (make-rat 6 -10))
(print-rat (make-rat 5 10))
(print-rat (make-rat -7 21))
