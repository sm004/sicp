(define (cont-frac n d k)
  (define (iter i result)
    (if (= i 0)
	result
	(iter (- i 1) (/ (n i)
			 (+ (d i) result)))))
  (iter k 0))

(define (tan-cf x k)
  (let ((y (* x x)))
    (cont-frac (lambda (i) (if (= i 1) x (- y)))
	       (lambda (i) (- (* 2 i) 1.0))
	       k)))

(tan-cf (/ 3.1416 4) 10)
