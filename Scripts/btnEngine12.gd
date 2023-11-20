extends Button

var module_scene = preload("res://module_12.tscn")

# Called when the node enters the scene tree for the first time.
func _ready():
	#pass # Replace with function body.
	connect("pressed", Callable(self, "_on_Button_pressed"))

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass

func _on_Button_pressed():
	print(module_scene)
	if module_scene:
		var module_instance = module_scene.instantiate()
		if module_instance:
			# Get the viewport size
			var viewport_size = get_viewport().size
			
			# Calculate the center position
			var center_position = Vector2(viewport_size.x / 2, viewport_size.y / 2)
			
			# Set the module's position to this center position
			module_instance.global_position = center_position
				
			get_tree().get_root().add_child(module_instance)
		else:
			print("Failed to instance the module.")
	else:
		print("Failed to load the PackedScene.")

