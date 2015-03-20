(define (iterative-improve good-enough? improve)
  (define (iter guess)
    (if (good-enough? guess)
	guess
	(iter (improve guess))))
  iter)

(define (sqrt x)
  (define (average x y) (/ (+ x y) 2))
  (define (improve guess)
    (average guess (/ x guess)))
  (define (good-enough? guess)
    (< (abs (- (square guess) x)) 0.001))
  ((iterative-improve good-enough? improve) 1.0))

(sqrt 4.0)

(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  ((iterative-improve
    (lambda (x) (close-enough? x (f x)))
    f)
   first-guess))

(fixed-point (lambda(x) (/ (+ x (/ 2.0 x)) 2)) 1.5)
