(define (problem shakey1)	
    (:domain shakey)
	(:objects
		xxD4321room1
		xxD4321room2
		xxD4321room3
		xxD4321box1
		xxD4321item1
		xxD4321item2		
		xxD4321item3
		xxD431room1
		xxD431room2
		xxD431room3
		xxD431box1
		xxD431item1
		xxD431item2		
		xxD431item3
		xxD421room1
		xxD421room2
		xxD421room3
		xxD421box1
		xxD421item1
		xxD421item2		
		xxD421item3
		xxD41room1
		xxD41room2
		xxD41room3
		xxD41box1
		xxD41item1
		xxD41item2		
		xxD41item3
		xxD321room1
		xxD321room2
		xxD321room3
		xxD321box1
		xxD321item1
		xxD321item2		
		xxD321item3
		xxD31room1
		xxD31room2
		xxD31room3
		xxD31box1
		xxD31item1
		xxD31item2		
		xxD31item3
		xxD21room1
		xxD21room2
		xxD21room3
		xxD21box1
		xxD21item1
		xxD21item2		
		xxD21item3
		xxD1room1
		xxD1room2
		xxD1room3
		xxD1box1
		xxD1item1
		xxD1item2		
		xxD1item3


		grip-left
		grip-right
	)

	(:init
		(ITEM xxD4321item1)
		(ITEM xxD4321item2)
		(ITEM xxD4321item3)
		(ROOM xxD4321room1) 
		(ROOM xxD4321room2)		
		(ROOM xxD4321room3)
		(DOOR xxD4321room1 xxD4321room2)
		(DOOR xxD4321room2 xxD4321room3)
		(WIDE-DOOR xxD4321room1 xxD4321room3)
		(BOX xxD4321box1)
		(box-location xxD4321room3 xxD4321box1)
		(item-location xxD4321item1 xxD4321room1)		
		(item-location xxD4321item2 xxD4321room1)		
		(item-location xxD4321item3 xxD4321room1)
		(ITEM xxD431item1)
		(ITEM xxD431item2)
		(ITEM xxD431item3)
		(ROOM xxD431room1) 
		(ROOM xxD431room2)		
		(ROOM xxD431room3)
		(DOOR xxD431room1 xxD431room2)
		(DOOR xxD431room2 xxD431room3)
		(WIDE-DOOR xxD431room1 xxD431room3)
		(BOX xxD431box1)
		(box-location xxD431room3 xxD431box1)
		(item-location xxD431item1 xxD431room1)		
		(item-location xxD431item2 xxD431room1)		
		(item-location xxD431item3 xxD431room1)
		(ITEM xxD421item1)
		(ITEM xxD421item2)
		(ITEM xxD421item3)
		(ROOM xxD421room1) 
		(ROOM xxD421room2)		
		(ROOM xxD421room3)
		(DOOR xxD421room1 xxD421room2)
		(DOOR xxD421room2 xxD421room3)
		(WIDE-DOOR xxD421room1 xxD421room3)
		(BOX xxD421box1)
		(box-location xxD421room3 xxD421box1)
		(item-location xxD421item1 xxD421room1)		
		(item-location xxD421item2 xxD421room1)		
		(item-location xxD421item3 xxD421room1)
		(ITEM xxD41item1)
		(ITEM xxD41item2)
		(ITEM xxD41item3)
		(ROOM xxD41room1) 
		(ROOM xxD41room2)		
		(ROOM xxD41room3)
		(DOOR xxD41room1 xxD41room2)
		(DOOR xxD41room2 xxD41room3)
		(WIDE-DOOR xxD41room1 xxD41room3)
		(BOX xxD41box1)
		(box-location xxD41room3 xxD41box1)
		(item-location xxD41item1 xxD41room1)		
		(item-location xxD41item2 xxD41room1)		
		(item-location xxD41item3 xxD41room1)
		(ITEM xxD321item1)
		(ITEM xxD321item2)
		(ITEM xxD321item3)
		(ROOM xxD321room1) 
		(ROOM xxD321room2)		
		(ROOM xxD321room3)
		(DOOR xxD321room1 xxD321room2)
		(DOOR xxD321room2 xxD321room3)
		(WIDE-DOOR xxD321room1 xxD321room3)
		(BOX xxD321box1)
		(box-location xxD321room3 xxD321box1)
		(item-location xxD321item1 xxD321room1)		
		(item-location xxD321item2 xxD321room1)		
		(item-location xxD321item3 xxD321room1)
		(ITEM xxD31item1)
		(ITEM xxD31item2)
		(ITEM xxD31item3)
		(ROOM xxD31room1) 
		(ROOM xxD31room2)		
		(ROOM xxD31room3)
		(DOOR xxD31room1 xxD31room2)
		(DOOR xxD31room2 xxD31room3)
		(WIDE-DOOR xxD31room1 xxD31room3)
		(BOX xxD31box1)
		(box-location xxD31room3 xxD31box1)
		(item-location xxD31item1 xxD31room1)		
		(item-location xxD31item2 xxD31room1)		
		(item-location xxD31item3 xxD31room1)
		(ITEM xxD21item1)
		(ITEM xxD21item2)
		(ITEM xxD21item3)
		(ROOM xxD21room1) 
		(ROOM xxD21room2)		
		(ROOM xxD21room3)
		(DOOR xxD21room1 xxD21room2)
		(DOOR xxD21room2 xxD21room3)
		(WIDE-DOOR xxD21room1 xxD21room3)
		(BOX xxD21box1)
		(box-location xxD21room3 xxD21box1)
		(item-location xxD21item1 xxD21room1)		
		(item-location xxD21item2 xxD21room1)		
		(item-location xxD21item3 xxD21room1)
		(ITEM xxD1item1)
		(ITEM xxD1item2)
		(ITEM xxD1item3)
		(ROOM xxD1room1) 
		(ROOM xxD1room2)		
		(ROOM xxD1room3)
		(DOOR xxD1room1 xxD1room2)
		(DOOR xxD1room2 xxD1room3)
		(WIDE-DOOR xxD1room1 xxD1room3)
		(BOX xxD1box1)
		(box-location xxD1room3 xxD1box1)
		(item-location xxD1item1 xxD1room1)		
		(item-location xxD1item2 xxD1room1)		
		(item-location xxD1item3 xxD1room1)

		(DOOR xxD1room1 xxD21room1)
		(DOOR xxD1room1 xxD31room1)
		(DOOR xxD31room1 xxD321room1)
		(DOOR xxD31room1 xxD41room1)
		(DOOR xxD41room1 xxD421room1)
		(DOOR xxD41room1 xxD431room1)
		(DOOR xxD431room1 xxD4321room1)


		(GRIP grip-left)
		(GRIP grip-right)
		(in-room xxD4321room1))
	(:goal 
		(and(item-location xxD4321item1 xxD4321room3)		
			(item-location xxD4321item2 xxD4321room3)		
			(item-location xxD4321item3 xxD4321room2)
			(not(is-lit xxD4321room1))
		(and(item-location xxD431item1 xxD431room3)		
			(item-location xxD431item2 xxD431room3)		
			(item-location xxD431item3 xxD431room2)
			(not(is-lit xxD431room1))
		)
		(and(item-location xxD41item1 xxD41room3)		
			(item-location xxD41item2 xxD41room3)		
			(item-location xxD41item3 xxD41room2)
			(not(is-lit xxD41room1))
		)
		(and(item-location xxD1item1 xxD1room3)		
			(item-location xxD1item2 xxD1room3)		
			(item-location xxD1item3 xxD1room2)
			(not(is-lit xxD1room1))
		)
))
)
