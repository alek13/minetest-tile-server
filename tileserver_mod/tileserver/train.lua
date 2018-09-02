
local update_formspec = function(meta)
	local inv = meta:get_inventory()

	local active = meta:get_int("active") == 1
	local state = "Inactive"

	if active then
		state = "Active"
	end

	local line = meta:get_string("line")
	local station = meta:get_string("station")
	local index = meta:get_string("index")

	meta:set_string("infotext", "Train: Line=" .. line .. ", Station=" .. station .. " (" .. state .. ")")

	meta:set_string("formspec", "size[8,3;]" ..
		-- col 1
		"field[0,1;4,1;line;Line;" .. name .. "]" ..
		"button_exit[4,1;4,1;save;Save]" ..

		-- col 2
		"field[0,2.5;4,1;station;Station;" .. station .. "]" ..
		"field[4,2.5;4,1;index;Index;" .. index .. "]" ..

		-- col 3
		"button_exit[4,3;4,1;toggle;Toggle]" ..
		"")

end


minetest.register_node("tileserver:train", {
	description = "Tileserver Train",
	tiles = {
		"tileserver_train.png",
		"tileserver_train.png",
		"tileserver_train.png",
		"tileserver_train.png",
		"tileserver_train.png",
		"tileserver_train.png"
	},
	groups = {cracky=3,oddly_breakable_by_hand=3},
	sounds = default.node_sound_glass_defaults(),

	can_dig = function(pos, player)
		local meta = minetest.env:get_meta(pos)
		local owner = meta:get_string("owner")

		return player and player:get_player_name() == owner
	end,

	after_place_node = function(pos, placer)
		local meta = minetest.get_meta(pos)
		meta:set_string("owner", placer:get_player_name() or "")
	end,

	on_construct = function(pos)
		local meta = minetest.get_meta(pos)

		meta:set_string("station", "")
		meta:set_string("line", "")
		meta:set_int("active", 0)
		meta:set_int("index", 0)

		update_formspec(meta)
	end,

	on_receive_fields = function(pos, formname, fields, sender)
		local meta = minetest.get_meta(pos)
		local playername = sender:get_player_name()

		if playername == meta:get_string("owner") then
			-- owner
			if fields.save then
				meta:set_string("line", fields.name)
				meta:set_string("station", fields.station)
				local index = tonumber(fields.index)
				if index ~= nil then
					meta:set_int("index", index)
				end
			end

			if fields.toggle then
				if meta:get_int("active") == 1 then
					meta:set_int("active", 0)
				else
					meta:set_int("active", 1)
				end
			end
		else
			-- non-owner
		end


		update_formspec(meta)
	end


})