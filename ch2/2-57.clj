(defn variable? [x] (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [e n]
  (and (number? e) (= n e)))

(defn sum? [x]
  (and (list? x) (= (first x) '+)))

(defn fold-right [f v x]
  (if (empty? x) v (f (first x) (fold-right f v (next x)))))

(defn append [s1 s2] (fold-right (fn [x y] (conj y x)) s2 s1))

(defn flatmap [f s] (fold-right append nil (map f s)))

(defn make-sum [a1 a2]
  (defn flatten-sum [s]
    (flatmap (fn [e] (if (sum? e)
                         (flatten-sum (next e))
                         (list e)))
             s))
  (defn reduce-numbers [s]
    (let [numbers (filter number? s)
          exps (filter (fn [x] (not (number? x))) s)]
         (conj exps (fold-right + 0 numbers))))
  (defn simplify-sum [s]
    (cond (=number? (first s) 0)
          (simplify-sum (next s))
          (nil? s)
          0
          (nil? (next s))
          (first s)
          :else (conj s '+)))
  (simplify-sum (reduce-numbers (flatten-sum (list a1 a2)))))

(defn addend [x] (fnext x))
(defn augend [x]
  (if (nil? (next (nnext x)))
      (first (nnext x))
      (conj (nnext x) '+)))

(defn product? [x]
  (and (list? x) (= (first x) '*)))

(defn make-product [m1 m2]
  (defn flatten-product [p]
    (flatmap (fn [e] (if (product? e)
                         (flatten-product (next e))
                         (list e)))
             p))
  (defn reduce-numbers [p]
    (let [numbers (filter number? p)
          exps (filter (fn [x] (not (number? x))) p)]
         (conj exps (fold-right * 1 numbers))))
  (defn simplify-product [p]
    (cond (=number? (first p) 0)
          0
          (=number? (first p) 1)
          (simplify-product (next p))
          (nil? p)
          1
          (nil? (next p))
          (first p)
          :else (conj p '*)))
  (simplify-product (reduce-numbers (flatten-product (list m1 m2)))))

(defn multiplier [x] (fnext x))
(defn multiplicand [x]
  (if (nil? (next (nnext x)))
      (first (nnext x))
      (conj (nnext x) '*)))

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

(println (derivative '(* x y (+ x 3)) 'x))
