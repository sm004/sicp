(define (numer x) (car x))

(define (denom x) (cdr x))

(define (make-rat n d)
  ((lambda (x y)
     (let ((g (abs (gcd x y))))
       (cons (/ x g) (/ y g))))
   (if (< d 0) (- n) n)
   (abs d)))

(define (gcd a b)
  (if (= b 0)
      a
      (gcd b (remainder a b))))

(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))

(print-rat (make-rat (- 2) (- 4)))
(print-rat (make-rat 6 (- 10)))
(print-rat (make-rat 5 10))
(print-rat (make-rat (- 7) 21))
