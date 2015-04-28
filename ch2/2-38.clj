(defn fold-right [op i s]
  (if (nil? s)
      i
      (op (first s) (fold-right op i (next s)))))

(defn fold-left [op i s]
  (if (nil? s)
      i
      (fold-left op (op i (first s)) (next s))))

(println (fold-right / 1 (list 1 2 3)))
(println (fold-left / 1 (list 1 2 3)))
(println (fold-right list nil (list 1 2 3)))
(println (fold-left list nil (list 1 2 3)))

;; if f is commutative and associative
;; foldr and foldl are same
;;
;; claim: (foldr f v xs) = (foldl f v xs) = (f [v & xs])
;;
;; base case:
;; (foldr f v ()) = v
;; (foldl f v ()) = v
;;
;; assuming true for xs:
;; (foldr f v (cons x xs)) = (f x (foldr f v xs)) = (f [x v & xs])
;; (foldl f v (cons x xs)) = (foldl f (f v x) xs) = (f [(f x v) & xs])
;; = (f [x v & xs])
