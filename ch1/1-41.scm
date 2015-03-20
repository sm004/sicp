(define (double f)
  (lambda (x) (f (f x))))

(define (inc x) (+ x 1))

(((double (double double)) inc) 5)
;; (d d) ((d d) f)
;; d (d ((d d) f))
;; d (d (d (d f)))
;; f ^ 16
