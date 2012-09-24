(define (problem shakey1)
	(:init 
		(ROOM room1) 
		(ROOM room2)
		(DOOR room1 room2)
		(in-room room1))

	(:goal 
		(in-room room2))
)
