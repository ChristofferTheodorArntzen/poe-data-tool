/* eslint-disable no-unused-vars */
import React from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ItemFilterTypes from "./ItemFilterTypes.json";

const ItemFilterList = (props) => {
	const listItemElements = ItemFilterTypes.itemTypes.map((type) => {
		const listItem = <ListItem key={type.id}>{type.name}</ListItem>;
		return listItem;
	});

	return (
		<div className="active-item-filter-list">
			<List id="itemFilter">{listItemElements}</List>
		</div>
	);
};

export default ItemFilterList;
