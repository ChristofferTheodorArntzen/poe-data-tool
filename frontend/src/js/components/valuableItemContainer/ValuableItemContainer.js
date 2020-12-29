/* eslint-disable no-unused-vars */
/*

Hente inn data fra endepunkt og i tillegg switche 
om til datadump når backend ikke går om mulig 
- han være en utils methode eller en greie for dev idk

- håndtere item img ved mindre skjerm ...


*/

import React, { Component } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";
import dataDump from "./dataDump.json";
import "./ValuableItemContainer.css";

class ValuableItemContainer extends Component {
	constructor() {
		super();

		this.state = {
			renderableItems: [],
		};

		this.handleClick = this.handleClick.bind(this);
	}

	componentDidMount() {
		const fetchedItems = dataDump.map((valuableItem) => {
			let renderableItem = {
				id: valuableItem.id,
				name: valuableItem.item.name,
				type: valuableItem.item.typeLine,
				stashId: valuableItem.item.inventoryId,
				svg: valuableItem.item.icon,
				price: 100,
				priceType: "chaos",
			};

			return renderableItem;
		});

		this.setState({
			renderableItems: fetchedItems,
		});
	}

	handleClick(event) {
		console.log(event);
	}

	render() {
		const useStyles = makeStyles({
			table: {
				minWidth: 650,
				maxWidth: 1000,
			},
		});

		return (
			<main>
				<div className="table-container">
					<TableContainer component={Paper}>
						<Table className={useStyles.table} aria-label="simple table">
							<TableHead>
								<TableRow>
									<TableCell>Img</TableCell>
									<TableCell algin="right">Name</TableCell>
									<TableCell algin="right">Type</TableCell>
									<TableCell algin="right">Stash ID</TableCell>
									<TableCell algin="right">Price</TableCell>
									<TableCell algin="right">PriceType</TableCell>
									<TableCell align="right"></TableCell>
								</TableRow>
							</TableHead>
							<TableBody>
								{this.state.renderableItems.map((item) => (
									<TableRow key={item.id}>
										<TableCell component="th" scope="row">
											<img src={item.svg} className="item-frame"></img>
										</TableCell>
										<TableCell>{item.name}</TableCell>
										<TableCell>{item.type}</TableCell>
										<TableCell>{item.stashId}</TableCell>
										<TableCell>{item.price}</TableCell>
										<TableCell>{item.priceType}</TableCell>
										<TableCell>
											<Button
												onClick={this.handleClick}
												variant="contained"
												color="secondary"
												size="small"
												startIcon={<DeleteIcon />}
											>
												Delete
											</Button>
										</TableCell>
									</TableRow>
								))}
							</TableBody>
						</Table>
					</TableContainer>
				</div>
			</main>
		);
	}
}
export default ValuableItemContainer;
