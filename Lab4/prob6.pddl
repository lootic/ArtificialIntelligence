(define (problem shakey1)	
    (:domain shakey)
	(:objects
		xxD654321room1
		xxD654321room2
		xxD654321room3
		xxD654321box1
		xxD654321item1
		xxD654321item2		
		xxD654321item3
		xxD65431room1
		xxD65431room2
		xxD65431room3
		xxD65431box1
		xxD65431item1
		xxD65431item2		
		xxD65431item3
		xxD65421room1
		xxD65421room2
		xxD65421room3
		xxD65421box1
		xxD65421item1
		xxD65421item2		
		xxD65421item3
		xxD6541room1
		xxD6541room2
		xxD6541room3
		xxD6541box1
		xxD6541item1
		xxD6541item2		
		xxD6541item3
		xxD65321room1
		xxD65321room2
		xxD65321room3
		xxD65321box1
		xxD65321item1
		xxD65321item2		
		xxD65321item3
		xxD6531room1
		xxD6531room2
		xxD6531room3
		xxD6531box1
		xxD6531item1
		xxD6531item2		
		xxD6531item3
		xxD6521room1
		xxD6521room2
		xxD6521room3
		xxD6521box1
		xxD6521item1
		xxD6521item2		
		xxD6521item3
		xxD651room1
		xxD651room2
		xxD651room3
		xxD651box1
		xxD651item1
		xxD651item2		
		xxD651item3
		xxD64321room1
		xxD64321room2
		xxD64321room3
		xxD64321box1
		xxD64321item1
		xxD64321item2		
		xxD64321item3
		xxD6431room1
		xxD6431room2
		xxD6431room3
		xxD6431box1
		xxD6431item1
		xxD6431item2		
		xxD6431item3
		xxD6421room1
		xxD6421room2
		xxD6421room3
		xxD6421box1
		xxD6421item1
		xxD6421item2		
		xxD6421item3
		xxD641room1
		xxD641room2
		xxD641room3
		xxD641box1
		xxD641item1
		xxD641item2		
		xxD641item3
		xxD6321room1
		xxD6321room2
		xxD6321room3
		xxD6321box1
		xxD6321item1
		xxD6321item2		
		xxD6321item3
		xxD631room1
		xxD631room2
		xxD631room3
		xxD631box1
		xxD631item1
		xxD631item2		
		xxD631item3
		xxD621room1
		xxD621room2
		xxD621room3
		xxD621box1
		xxD621item1
		xxD621item2		
		xxD621item3
		xxD61room1
		xxD61room2
		xxD61room3
		xxD61box1
		xxD61item1
		xxD61item2		
		xxD61item3
		xxD54321room1
		xxD54321room2
		xxD54321room3
		xxD54321box1
		xxD54321item1
		xxD54321item2		
		xxD54321item3
		xxD5431room1
		xxD5431room2
		xxD5431room3
		xxD5431box1
		xxD5431item1
		xxD5431item2		
		xxD5431item3
		xxD5421room1
		xxD5421room2
		xxD5421room3
		xxD5421box1
		xxD5421item1
		xxD5421item2		
		xxD5421item3
		xxD541room1
		xxD541room2
		xxD541room3
		xxD541box1
		xxD541item1
		xxD541item2		
		xxD541item3
		xxD5321room1
		xxD5321room2
		xxD5321room3
		xxD5321box1
		xxD5321item1
		xxD5321item2		
		xxD5321item3
		xxD531room1
		xxD531room2
		xxD531room3
		xxD531box1
		xxD531item1
		xxD531item2		
		xxD531item3
		xxD521room1
		xxD521room2
		xxD521room3
		xxD521box1
		xxD521item1
		xxD521item2		
		xxD521item3
		xxD51room1
		xxD51room2
		xxD51room3
		xxD51box1
		xxD51item1
		xxD51item2		
		xxD51item3
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
		(ITEM xxD654321item1)
		(ITEM xxD654321item2)
		(ITEM xxD654321item3)
		(ROOM xxD654321room1) 
		(ROOM xxD654321room2)		
		(ROOM xxD654321room3)
		(DOOR xxD654321room1 xxD654321room2)
		(DOOR xxD654321room2 xxD654321room3)
		(WIDE-DOOR xxD654321room1 xxD654321room3)
		(BOX xxD654321box1)
		(box-location xxD654321room3 xxD654321box1)
		(item-location xxD654321item1 xxD654321room1)		
		(item-location xxD654321item2 xxD654321room1)		
		(item-location xxD654321item3 xxD654321room1)
		(ITEM xxD65431item1)
		(ITEM xxD65431item2)
		(ITEM xxD65431item3)
		(ROOM xxD65431room1) 
		(ROOM xxD65431room2)		
		(ROOM xxD65431room3)
		(DOOR xxD65431room1 xxD65431room2)
		(DOOR xxD65431room2 xxD65431room3)
		(WIDE-DOOR xxD65431room1 xxD65431room3)
		(BOX xxD65431box1)
		(box-location xxD65431room3 xxD65431box1)
		(item-location xxD65431item1 xxD65431room1)		
		(item-location xxD65431item2 xxD65431room1)		
		(item-location xxD65431item3 xxD65431room1)
		(ITEM xxD65421item1)
		(ITEM xxD65421item2)
		(ITEM xxD65421item3)
		(ROOM xxD65421room1) 
		(ROOM xxD65421room2)		
		(ROOM xxD65421room3)
		(DOOR xxD65421room1 xxD65421room2)
		(DOOR xxD65421room2 xxD65421room3)
		(WIDE-DOOR xxD65421room1 xxD65421room3)
		(BOX xxD65421box1)
		(box-location xxD65421room3 xxD65421box1)
		(item-location xxD65421item1 xxD65421room1)		
		(item-location xxD65421item2 xxD65421room1)		
		(item-location xxD65421item3 xxD65421room1)
		(ITEM xxD6541item1)
		(ITEM xxD6541item2)
		(ITEM xxD6541item3)
		(ROOM xxD6541room1) 
		(ROOM xxD6541room2)		
		(ROOM xxD6541room3)
		(DOOR xxD6541room1 xxD6541room2)
		(DOOR xxD6541room2 xxD6541room3)
		(WIDE-DOOR xxD6541room1 xxD6541room3)
		(BOX xxD6541box1)
		(box-location xxD6541room3 xxD6541box1)
		(item-location xxD6541item1 xxD6541room1)		
		(item-location xxD6541item2 xxD6541room1)		
		(item-location xxD6541item3 xxD6541room1)
		(ITEM xxD65321item1)
		(ITEM xxD65321item2)
		(ITEM xxD65321item3)
		(ROOM xxD65321room1) 
		(ROOM xxD65321room2)		
		(ROOM xxD65321room3)
		(DOOR xxD65321room1 xxD65321room2)
		(DOOR xxD65321room2 xxD65321room3)
		(WIDE-DOOR xxD65321room1 xxD65321room3)
		(BOX xxD65321box1)
		(box-location xxD65321room3 xxD65321box1)
		(item-location xxD65321item1 xxD65321room1)		
		(item-location xxD65321item2 xxD65321room1)		
		(item-location xxD65321item3 xxD65321room1)
		(ITEM xxD6531item1)
		(ITEM xxD6531item2)
		(ITEM xxD6531item3)
		(ROOM xxD6531room1) 
		(ROOM xxD6531room2)		
		(ROOM xxD6531room3)
		(DOOR xxD6531room1 xxD6531room2)
		(DOOR xxD6531room2 xxD6531room3)
		(WIDE-DOOR xxD6531room1 xxD6531room3)
		(BOX xxD6531box1)
		(box-location xxD6531room3 xxD6531box1)
		(item-location xxD6531item1 xxD6531room1)		
		(item-location xxD6531item2 xxD6531room1)		
		(item-location xxD6531item3 xxD6531room1)
		(ITEM xxD6521item1)
		(ITEM xxD6521item2)
		(ITEM xxD6521item3)
		(ROOM xxD6521room1) 
		(ROOM xxD6521room2)		
		(ROOM xxD6521room3)
		(DOOR xxD6521room1 xxD6521room2)
		(DOOR xxD6521room2 xxD6521room3)
		(WIDE-DOOR xxD6521room1 xxD6521room3)
		(BOX xxD6521box1)
		(box-location xxD6521room3 xxD6521box1)
		(item-location xxD6521item1 xxD6521room1)		
		(item-location xxD6521item2 xxD6521room1)		
		(item-location xxD6521item3 xxD6521room1)
		(ITEM xxD651item1)
		(ITEM xxD651item2)
		(ITEM xxD651item3)
		(ROOM xxD651room1) 
		(ROOM xxD651room2)		
		(ROOM xxD651room3)
		(DOOR xxD651room1 xxD651room2)
		(DOOR xxD651room2 xxD651room3)
		(WIDE-DOOR xxD651room1 xxD651room3)
		(BOX xxD651box1)
		(box-location xxD651room3 xxD651box1)
		(item-location xxD651item1 xxD651room1)		
		(item-location xxD651item2 xxD651room1)		
		(item-location xxD651item3 xxD651room1)
		(ITEM xxD64321item1)
		(ITEM xxD64321item2)
		(ITEM xxD64321item3)
		(ROOM xxD64321room1) 
		(ROOM xxD64321room2)		
		(ROOM xxD64321room3)
		(DOOR xxD64321room1 xxD64321room2)
		(DOOR xxD64321room2 xxD64321room3)
		(WIDE-DOOR xxD64321room1 xxD64321room3)
		(BOX xxD64321box1)
		(box-location xxD64321room3 xxD64321box1)
		(item-location xxD64321item1 xxD64321room1)		
		(item-location xxD64321item2 xxD64321room1)		
		(item-location xxD64321item3 xxD64321room1)
		(ITEM xxD6431item1)
		(ITEM xxD6431item2)
		(ITEM xxD6431item3)
		(ROOM xxD6431room1) 
		(ROOM xxD6431room2)		
		(ROOM xxD6431room3)
		(DOOR xxD6431room1 xxD6431room2)
		(DOOR xxD6431room2 xxD6431room3)
		(WIDE-DOOR xxD6431room1 xxD6431room3)
		(BOX xxD6431box1)
		(box-location xxD6431room3 xxD6431box1)
		(item-location xxD6431item1 xxD6431room1)		
		(item-location xxD6431item2 xxD6431room1)		
		(item-location xxD6431item3 xxD6431room1)
		(ITEM xxD6421item1)
		(ITEM xxD6421item2)
		(ITEM xxD6421item3)
		(ROOM xxD6421room1) 
		(ROOM xxD6421room2)		
		(ROOM xxD6421room3)
		(DOOR xxD6421room1 xxD6421room2)
		(DOOR xxD6421room2 xxD6421room3)
		(WIDE-DOOR xxD6421room1 xxD6421room3)
		(BOX xxD6421box1)
		(box-location xxD6421room3 xxD6421box1)
		(item-location xxD6421item1 xxD6421room1)		
		(item-location xxD6421item2 xxD6421room1)		
		(item-location xxD6421item3 xxD6421room1)
		(ITEM xxD641item1)
		(ITEM xxD641item2)
		(ITEM xxD641item3)
		(ROOM xxD641room1) 
		(ROOM xxD641room2)		
		(ROOM xxD641room3)
		(DOOR xxD641room1 xxD641room2)
		(DOOR xxD641room2 xxD641room3)
		(WIDE-DOOR xxD641room1 xxD641room3)
		(BOX xxD641box1)
		(box-location xxD641room3 xxD641box1)
		(item-location xxD641item1 xxD641room1)		
		(item-location xxD641item2 xxD641room1)		
		(item-location xxD641item3 xxD641room1)
		(ITEM xxD6321item1)
		(ITEM xxD6321item2)
		(ITEM xxD6321item3)
		(ROOM xxD6321room1) 
		(ROOM xxD6321room2)		
		(ROOM xxD6321room3)
		(DOOR xxD6321room1 xxD6321room2)
		(DOOR xxD6321room2 xxD6321room3)
		(WIDE-DOOR xxD6321room1 xxD6321room3)
		(BOX xxD6321box1)
		(box-location xxD6321room3 xxD6321box1)
		(item-location xxD6321item1 xxD6321room1)		
		(item-location xxD6321item2 xxD6321room1)		
		(item-location xxD6321item3 xxD6321room1)
		(ITEM xxD631item1)
		(ITEM xxD631item2)
		(ITEM xxD631item3)
		(ROOM xxD631room1) 
		(ROOM xxD631room2)		
		(ROOM xxD631room3)
		(DOOR xxD631room1 xxD631room2)
		(DOOR xxD631room2 xxD631room3)
		(WIDE-DOOR xxD631room1 xxD631room3)
		(BOX xxD631box1)
		(box-location xxD631room3 xxD631box1)
		(item-location xxD631item1 xxD631room1)		
		(item-location xxD631item2 xxD631room1)		
		(item-location xxD631item3 xxD631room1)
		(ITEM xxD621item1)
		(ITEM xxD621item2)
		(ITEM xxD621item3)
		(ROOM xxD621room1) 
		(ROOM xxD621room2)		
		(ROOM xxD621room3)
		(DOOR xxD621room1 xxD621room2)
		(DOOR xxD621room2 xxD621room3)
		(WIDE-DOOR xxD621room1 xxD621room3)
		(BOX xxD621box1)
		(box-location xxD621room3 xxD621box1)
		(item-location xxD621item1 xxD621room1)		
		(item-location xxD621item2 xxD621room1)		
		(item-location xxD621item3 xxD621room1)
		(ITEM xxD61item1)
		(ITEM xxD61item2)
		(ITEM xxD61item3)
		(ROOM xxD61room1) 
		(ROOM xxD61room2)		
		(ROOM xxD61room3)
		(DOOR xxD61room1 xxD61room2)
		(DOOR xxD61room2 xxD61room3)
		(WIDE-DOOR xxD61room1 xxD61room3)
		(BOX xxD61box1)
		(box-location xxD61room3 xxD61box1)
		(item-location xxD61item1 xxD61room1)		
		(item-location xxD61item2 xxD61room1)		
		(item-location xxD61item3 xxD61room1)
		(ITEM xxD54321item1)
		(ITEM xxD54321item2)
		(ITEM xxD54321item3)
		(ROOM xxD54321room1) 
		(ROOM xxD54321room2)		
		(ROOM xxD54321room3)
		(DOOR xxD54321room1 xxD54321room2)
		(DOOR xxD54321room2 xxD54321room3)
		(WIDE-DOOR xxD54321room1 xxD54321room3)
		(BOX xxD54321box1)
		(box-location xxD54321room3 xxD54321box1)
		(item-location xxD54321item1 xxD54321room1)		
		(item-location xxD54321item2 xxD54321room1)		
		(item-location xxD54321item3 xxD54321room1)
		(ITEM xxD5431item1)
		(ITEM xxD5431item2)
		(ITEM xxD5431item3)
		(ROOM xxD5431room1) 
		(ROOM xxD5431room2)		
		(ROOM xxD5431room3)
		(DOOR xxD5431room1 xxD5431room2)
		(DOOR xxD5431room2 xxD5431room3)
		(WIDE-DOOR xxD5431room1 xxD5431room3)
		(BOX xxD5431box1)
		(box-location xxD5431room3 xxD5431box1)
		(item-location xxD5431item1 xxD5431room1)		
		(item-location xxD5431item2 xxD5431room1)		
		(item-location xxD5431item3 xxD5431room1)
		(ITEM xxD5421item1)
		(ITEM xxD5421item2)
		(ITEM xxD5421item3)
		(ROOM xxD5421room1) 
		(ROOM xxD5421room2)		
		(ROOM xxD5421room3)
		(DOOR xxD5421room1 xxD5421room2)
		(DOOR xxD5421room2 xxD5421room3)
		(WIDE-DOOR xxD5421room1 xxD5421room3)
		(BOX xxD5421box1)
		(box-location xxD5421room3 xxD5421box1)
		(item-location xxD5421item1 xxD5421room1)		
		(item-location xxD5421item2 xxD5421room1)		
		(item-location xxD5421item3 xxD5421room1)
		(ITEM xxD541item1)
		(ITEM xxD541item2)
		(ITEM xxD541item3)
		(ROOM xxD541room1) 
		(ROOM xxD541room2)		
		(ROOM xxD541room3)
		(DOOR xxD541room1 xxD541room2)
		(DOOR xxD541room2 xxD541room3)
		(WIDE-DOOR xxD541room1 xxD541room3)
		(BOX xxD541box1)
		(box-location xxD541room3 xxD541box1)
		(item-location xxD541item1 xxD541room1)		
		(item-location xxD541item2 xxD541room1)		
		(item-location xxD541item3 xxD541room1)
		(ITEM xxD5321item1)
		(ITEM xxD5321item2)
		(ITEM xxD5321item3)
		(ROOM xxD5321room1) 
		(ROOM xxD5321room2)		
		(ROOM xxD5321room3)
		(DOOR xxD5321room1 xxD5321room2)
		(DOOR xxD5321room2 xxD5321room3)
		(WIDE-DOOR xxD5321room1 xxD5321room3)
		(BOX xxD5321box1)
		(box-location xxD5321room3 xxD5321box1)
		(item-location xxD5321item1 xxD5321room1)		
		(item-location xxD5321item2 xxD5321room1)		
		(item-location xxD5321item3 xxD5321room1)
		(ITEM xxD531item1)
		(ITEM xxD531item2)
		(ITEM xxD531item3)
		(ROOM xxD531room1) 
		(ROOM xxD531room2)		
		(ROOM xxD531room3)
		(DOOR xxD531room1 xxD531room2)
		(DOOR xxD531room2 xxD531room3)
		(WIDE-DOOR xxD531room1 xxD531room3)
		(BOX xxD531box1)
		(box-location xxD531room3 xxD531box1)
		(item-location xxD531item1 xxD531room1)		
		(item-location xxD531item2 xxD531room1)		
		(item-location xxD531item3 xxD531room1)
		(ITEM xxD521item1)
		(ITEM xxD521item2)
		(ITEM xxD521item3)
		(ROOM xxD521room1) 
		(ROOM xxD521room2)		
		(ROOM xxD521room3)
		(DOOR xxD521room1 xxD521room2)
		(DOOR xxD521room2 xxD521room3)
		(WIDE-DOOR xxD521room1 xxD521room3)
		(BOX xxD521box1)
		(box-location xxD521room3 xxD521box1)
		(item-location xxD521item1 xxD521room1)		
		(item-location xxD521item2 xxD521room1)		
		(item-location xxD521item3 xxD521room1)
		(ITEM xxD51item1)
		(ITEM xxD51item2)
		(ITEM xxD51item3)
		(ROOM xxD51room1) 
		(ROOM xxD51room2)		
		(ROOM xxD51room3)
		(DOOR xxD51room1 xxD51room2)
		(DOOR xxD51room2 xxD51room3)
		(WIDE-DOOR xxD51room1 xxD51room3)
		(BOX xxD51box1)
		(box-location xxD51room3 xxD51box1)
		(item-location xxD51item1 xxD51room1)		
		(item-location xxD51item2 xxD51room1)		
		(item-location xxD51item3 xxD51room1)
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
		(DOOR xxD431room1 xxD51room1)
		(DOOR xxD51room1 xxD521room1)
		(DOOR xxD51room1 xxD531room1)
		(DOOR xxD531room1 xxD5321room1)
		(DOOR xxD531room1 xxD541room1)
		(DOOR xxD541room1 xxD5421room1)
		(DOOR xxD541room1 xxD5431room1)
		(DOOR xxD5431room1 xxD54321room1)
		(DOOR xxD5431room1 xxD61room1)
		(DOOR xxD61room1 xxD621room1)
		(DOOR xxD61room1 xxD631room1)
		(DOOR xxD631room1 xxD6321room1)
		(DOOR xxD631room1 xxD641room1)
		(DOOR xxD641room1 xxD6421room1)
		(DOOR xxD641room1 xxD6431room1)
		(DOOR xxD6431room1 xxD64321room1)
		(DOOR xxD6431room1 xxD651room1)
		(DOOR xxD651room1 xxD6521room1)
		(DOOR xxD651room1 xxD6531room1)
		(DOOR xxD6531room1 xxD65321room1)
		(DOOR xxD6531room1 xxD6541room1)
		(DOOR xxD6541room1 xxD65421room1)
		(DOOR xxD6541room1 xxD65431room1)
		(DOOR xxD65431room1 xxD654321room1)


		(GRIP grip-left)
		(GRIP grip-right)
		(in-room xxD654321room1))
	(:goal 
		(and(item-location xxD654321item1 xxD654321room3)		
			(item-location xxD654321item2 xxD654321room3)		
			(item-location xxD654321item3 xxD654321room2)
			(not(is-lit xxD654321room1))
		(and(item-location xxD65431item1 xxD65431room3)		
			(item-location xxD65431item2 xxD65431room3)		
			(item-location xxD65431item3 xxD65431room2)
			(not(is-lit xxD65431room1))
		)
		(and(item-location xxD6541item1 xxD6541room3)		
			(item-location xxD6541item2 xxD6541room3)		
			(item-location xxD6541item3 xxD6541room2)
			(not(is-lit xxD6541room1))
		)
		(and(item-location xxD651item1 xxD651room3)		
			(item-location xxD651item2 xxD651room3)		
			(item-location xxD651item3 xxD651room2)
			(not(is-lit xxD651room1))
		)
		(and(item-location xxD64321item1 xxD64321room3)		
			(item-location xxD64321item2 xxD64321room3)		
			(item-location xxD64321item3 xxD64321room2)
			(not(is-lit xxD64321room1))
		(and(item-location xxD6431item1 xxD6431room3)		
			(item-location xxD6431item2 xxD6431room3)		
			(item-location xxD6431item3 xxD6431room2)
			(not(is-lit xxD6431room1))
		)
		(and(item-location xxD641item1 xxD641room3)		
			(item-location xxD641item2 xxD641room3)		
			(item-location xxD641item3 xxD641room2)
			(not(is-lit xxD641room1))
		)
		(and(item-location xxD61item1 xxD61room3)		
			(item-location xxD61item2 xxD61room3)		
			(item-location xxD61item3 xxD61room2)
			(not(is-lit xxD61room1))
		)

		(and(item-location xxD54321item1 xxD54321room3)		
			(item-location xxD54321item2 xxD54321room3)		
			(item-location xxD54321item3 xxD54321room2)
			(not(is-lit xxD54321room1))
		(and(item-location xxD5431item1 xxD5431room3)		
			(item-location xxD5431item2 xxD5431room3)		
			(item-location xxD5431item3 xxD5431room2)
			(not(is-lit xxD5431room1))
		)
		(and(item-location xxD541item1 xxD541room3)		
			(item-location xxD541item2 xxD541room3)		
			(item-location xxD541item3 xxD541room2)
			(not(is-lit xxD541room1))
		)
		(and(item-location xxD51item1 xxD51room3)		
			(item-location xxD51item2 xxD51room3)		
			(item-location xxD51item3 xxD51room2)
			(not(is-lit xxD51room1))
		)
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
)))
)
