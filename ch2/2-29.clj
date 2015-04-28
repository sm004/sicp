(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(defn left-branch [mobile]
  (first mobile))
(defn right-branch [mobile]
  (first (next mobile)))
(defn mobile? [structure]
  (list? structure))

(defn branch-length [branch]
  (first branch))
(defn branch-structure [branch]
  (first (next branch)))

(defn total-weight [mobile]
  (defn branch-weight [branch]
    (let
      [s (branch-structure branch)]
      (if (mobile? s)
          (total-weight s)
          s)))
  (+ (branch-weight (left-branch mobile))
     (branch-weight (right-branch mobile))))

;; better ways of doing this?
(defn branch-weight [branch]
  (let
    [s (branch-structure branch)]
    (if (mobile? s)
        (total-weight s)
        s)))

(defn balanced? [mobile]
  (let
    [left (left-branch mobile)
     right (right-branch mobile)
     ls (branch-structure left)
     rs (branch-structure right)]
  (and (if (mobile? ls) (balanced? ls) true)
       (if (mobile? rs) (balanced? rs) true)
       (= (* (branch-weight left) (branch-length left))
          (* (branch-weight right) (branch-length right))))))

(def balanced-mobile
  (make-mobile
    (make-branch 10 4)
    (make-branch 5 (make-mobile
                     (make-branch 5 4)
                     (make-branch 5 4)))))
(def unbalanced-mobile
  (make-mobile
    (make-branch 10 4)
    (make-branch 5 (make-mobile
                     (make-branch 5 4)
                     (make-branch 5 2)))))

(println (total-weight balanced-mobile))
(println (balanced? balanced-mobile))
(println (total-weight unbalanced-mobile))
(println (balanced? unbalanced-mobile))
