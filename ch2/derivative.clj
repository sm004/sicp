(defn variable? [x] (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [e n]
  (and (number? e) (= n e)))

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn sum? [x]
  (and (list? x) (= (first x) '+)))

(defn addend [x] (fnext x))
(defn augend [x] (fnext (next x)))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))

(defn product? [x]
  (and (list? x) (= (first x) '*)))

(defn multiplier [x] (fnext x))
(defn multiplicand [x] (fnext (next x)))

(defn derivative [e v]
  (cond (number? e)
        0
        (variable? e)
        (if (same-variable? v e) 1 0)
        (sum? e)
        (make-sum (derivative (addend e) v)
                  (derivative (augend e) v))
        (product? e)
        (let [e1 (multiplier e)
              e2 (multiplicand e)]
             (make-sum (make-product e1 (derivative e2 v))
                       (make-product (derivative e1 v) e2)))
        :else
        (throw (Exception. "unknown expression type - DERIVATIVE"))))

(println (derivative '(* (* x y) (+ x 3)) 'x))
