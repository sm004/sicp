(define (cube x) (* x x x))
(define (p x) (- (* 3 x) (* 4 (cube x))))
(define (sine angle count)
   (if (not (> (abs angle) 0.1))
       (begin (display count)
	      angle)
       (p (sine (/ angle 3.0) (+ count 1)))))

(sine 12.15 0)

;; space: log a
;; steps: log a
