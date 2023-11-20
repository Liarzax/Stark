extends Sprite2D

var dragging = false
var drag_offset = Vector2()
var grid_size = 64  # Adjust this value based on your module size and desired grid size

func _ready():
	set_process_input(true)

func _input(event):
	if event is InputEventMouseButton and event.button_index == MOUSE_BUTTON_LEFT:
		if event.pressed and get_rect().has_point(to_local(event.position)):
			dragging = true
			drag_offset = to_local(event.position)  # Convert to local coordinates
		elif not event.pressed:
			dragging = false

	if event is InputEventMouseMotion and dragging:
		var mouse_pos = get_global_mouse_position()
		var adjusted_pos = mouse_pos - drag_offset  # Adjust for the click offset
		global_position = adjusted_pos.snapped(Vector2(grid_size, grid_size))


