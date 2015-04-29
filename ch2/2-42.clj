(defn fold-right [op i s]
  (if (nil? s)
      i
      (op (first s)
          (fold-right op i (next s)))))

(defn append [s1 s2]
  (fold-right cons s2 s1))

(defn flatmap [proc s]
  (fold-right append nil (map proc s)))

(def empty-board '())

(defn adjoin-position [row k positions]
  (cons (list k row) positions))

(defn any [s]
  (fold-right (fn [x y] (if x true y))
              false
              s))

(defn row-attack? [q ps]
  (any (map (fn [p]
              (= (fnext q) (fnext p)))
            ps)))

(defn abs [x]
  (if (< x 0) (- x) x))

(defn diag-attack? [q ps]
  (any (map (fn [p]
              (= (abs (- (first q) (first p)))
                 (abs (- (fnext q) (fnext p)))))
            ps)))

(defn safe? [k positions]
  (and (not (row-attack? (first positions) (next positions)))
       (not (diag-attack? (first positions) (next positions)))))

(defn queens [board-size]
  (defn queen-cols [k]
    (if (= k 0)
        (list empty-board)
        (filter
         (fn [positions] (safe? k positions))
         (flatmap
          (fn [rest-of-queens]
            (map (fn [new-row]
                   (adjoin-position new-row k rest-of-queens))
                 (range 1 (+ board-size 1))))
          (queen-cols (- k 1))))))
  (queen-cols board-size))

(println (queens 8))
