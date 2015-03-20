(define (filtered-accumulate pred combiner null-val term a next b)
  (define (iter a result)
    (if (> a b)
	result
	(iter (next a) (combiner result
				 (if (pred a)
				     (term a)
				     null-val)))))
  (iter a null-val))


(define (smallest-divisor n)
  (find-divisor n 2))
(define (next-divisor n)
  (if (= n 2) 3 (+ n 2)))
(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (next-divisor test-divisor)))))
(define (divides? a b)
  (= (remainder b a) 0))
(define (prime? n)
  (= n (smallest-divisor n)))

(define (square x) (* x x))
(define (inc x) (+ x 1))

(define (sumsq-prime a b)
  (filtered-accumulate prime? + 0 square a inc b))

(sumsq-prime 2 10)

(define (gcd a b)
  (if (= b 0) a
      (gcd b (remainder a b))))

(define (identity x) x)

(define (product-relative-prime n)
  (define (relative-prime? k)
    (= (gcd n k) 1))
  (filtered-accumulate relative-prime? * 1 identity 1 inc (- n 1)))

(product-relative-prime 7)
