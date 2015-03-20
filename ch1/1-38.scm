(define (cont-frac n d k)
  (define (iter i result)
    (if (= i 0)
	result
	(iter (- i 1) (/ (n i)
			 (+ (d i) result)))))
  (iter k 0))

(define (e-minus-2-approx k)
  (cont-frac (lambda (i) 1.0)
	     (lambda (i) (if (or (= (remainder i 3) 1)
				 (= (remainder i 3) 0))
			     1
			     (* 2 (+ 1 (quotient i 3)))))
	     k))
(define (e-approx k)
  (+ 2 (e-minus-2-approx k)))

(e-approx 100)
