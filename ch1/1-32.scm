(define (accumulate combiner null-value term a next b)
  (define (iter a result)
    (if (> a b)
	result
	(iter (next a) (combiner result (term a)))))
  (iter a null-value))

(define (accumulate-recur combiner null-value term a next b)
  (if (> a b)
      null-value
      (combiner (term a)
		(accumulate-recur combiner
				  null-value
				  term
				  (next a)
				  next
				  b))))

(define (sum term a next b) (accumulate + 0 term a next b))
(define (product term a next b) (accumulate * 1 term a next b))
(define (sum-recur term a next b)
  (accumulate-recur + 0 term a next b))
(define (product-recur term a next b)
  (accumulate-recur * 1 term a next b))

(define (identity x) x)
(define (inc x) (+ x 1))

(define (sum-to-n n) (sum identity 1 inc n))
(define (factorial n) (product identity 1 inc n))
(define (sum-to-n-recur n) (sum-recur identity 1 inc n))
(define (factorial-recur n) (product-recur identity 1 inc n))

(sum-to-n 100)
(sum-to-n-recur 100)
(factorial 5)
(factorial-recur 5)
