(define (f g)
  (g 2))

;; applicative order
;; (f f)
;; (f 2)
;; (2 2)
;; error

(f f)
