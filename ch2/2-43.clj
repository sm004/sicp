;; Louis' program evaluates (queen-cols (- k 1)) b times for each k
;; b is board-size
;;
;; T(b, k) = T(b, k - 1) + c * b * S(b, k - 1)
;; S(b, k - 1) = # solutions
;;
;; For Louis' program
;; Tl(b, k) = b * Tl(b, k - 1) + c * b * S(b, k - 1)
;; Tl(b, b) grows atleast as fast as b^b
