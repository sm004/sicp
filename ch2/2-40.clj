(defn divides? [a b]
  (= (rem b a) 0))

(defn square [x] (* x x))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

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

(defn prime-sum? [pair]
  (prime? (+ (first pair) (first (next pair)))))

(defn make-pair-sum [pair]
  (list (first pair) (fnext pair) (+ (first pair) (fnext pair))))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum?
               (unique-pairs n))))

(println (prime-sum-pairs 6))
