(defn fold-right [op i s]
  (if (nil? s)
      i
      (op (first s)
          (fold-right op i (next s)))))

(defn append [s1 s2]
  (fold-right cons s2 s1))

(defn flatmap [proc s]
  (fold-right append nil (map proc s)))

(defn unique-pairs [n]
  (flatmap (fn [i]
             (map (fn [j] (list j i))
                  (range 1 i)))
           (range 2 (+ n 1))))

(defn sum-to? [t s]
  (= s (+ (first t) (fnext t) (fnext (next t)))))

(defn cartesian-product [s1 s2]
  (flatmap (fn [x1]
             (map (fn [x2] (list x1 x2)) s2))
           s1))

(defn ordered-triples [n]
  (def s (range 1 (+ n 1)))
  (map (fn [x] (cons (first x) (fnext x)))
       (cartesian-product s (cartesian-product s s))))

(defn ordered-triple-sum [n s]
  (filter (fn [t] (sum-to? t s))
          (ordered-triples n)))

(println (ordered-triple-sum 10 25))
