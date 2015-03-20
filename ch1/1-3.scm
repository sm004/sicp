(define (sq x) (* x x))

(define (sumsq x y) (+ (sq x) (sq y)))

(define (larger-sumsq x y z)
  (cond ((and (>= x z) (>= y z)) (sumsq x y))
	((and (>= x y) (>= z y)) (sumsq x z))
	(else (sumsq y z))))

(larger-sumsq 1 2 3)

(larger-sumsq 3 2 1)

(larger-sumsq 2 1 3)
