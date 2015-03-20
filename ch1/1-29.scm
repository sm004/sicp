(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term (next a) next b))))

(define (integral f a b dx)
  (define (add-dx x) (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
     dx))

(define (cube x) (* x x x))

(integral cube 0 1 0.01)
(integral cube 0 1 0.001)

(define (simpsons-rule f a b n)
  (define (inc x) (+ x 1))
  (define (helper h)
    (define (value-at k) (f (+ a (* k h))))
    (define (term k)
      (if (even? k)
	  (* 2 (value-at k))
	  (* 4 (value-at k))))
    (+ (value-at 0)
       (value-at n)
       (sum term 1 inc (- n 1))))
  (* (/ (- b a) (* 3.0 n)) (helper (/ (- b a) n))))

(simpsons-rule cube 0 1 100)
(simpsons-rule cube 0 1 1000)
