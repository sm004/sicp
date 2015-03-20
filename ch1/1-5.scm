(define (p) (p))

(define (test x y)
  (if (= x 0)
      0
      y))

;; fails for applicative-order eval
;; returns 0 for normal order
(test 0 p)
