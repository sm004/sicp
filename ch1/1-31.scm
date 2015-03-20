(define (product term a next b)
  (if (> a b)
      1
      (* (term a)
	 (product term (next a) next b))))

(define (product-iter term a next b)
  (define (iter a result)
    (if (> a b)
	result
	(iter (next a) (* result (term a)))))
  (iter a 1))

(define (identity x) x)
(define (inc x) (+ x 1))

(define (factorial n) (product identity 1 inc n))
(define (factorial-iter n) (product-iter identity 1 inc n))

(factorial 5)
(factorial-iter 5)

(define (pi-by-4-approx n)
  (define (next a) (+ a 2))
  (define (square x) (* x x))
  (define (term a)
    (/ (* a (+ a 2))
       (square (+ 1.0 a))))
  (product-iter term 2 next n))
(define (pi-approx n)
  (* 4 (pi-by-4-approx n)))

(pi-approx 10000)
