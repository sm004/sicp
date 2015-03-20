(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define (try guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
	  ;(begin
	  ;  (display guess)
	  ;  (newline)
	  ;  (try next)))))
	  (try next))))
  (try first-guess))

(define (average x y) (/ (+ x y) 2))

(define (average-damp f)
  (lambda (x) (average x (f x))))

(define (compose f g)
  (lambda (x) (f (g x))))

(define (repeated f n)
  (if (= n 1)
      f
      (compose f (repeated f (- n 1)))))

;; log2 here is the number of times x/y^(n-1) needs to be damped
(define (log2 x)
  (cond ((< x 1) 0)
	((< x 4) 1)
	(else (+ (log2 (/ x 2)) 1))))

(define (power base m)
  (cond ((= m 0) 1)
	((even? m) (power (square base) (/ m 2)))
	(else (* base (power base (- m 1))))))

(define (nth-root n x)
  (fixed-point
   ((repeated average-damp (log2 n))
    (lambda (y) (/ x (power y (- n 1)))))
  1.0))

(define powers '(2 3 4 5 6 7 8 9 10 11 12 13 14 15))
(map (lambda (p) (nth-root p (power 2 p))) powers)
