
/*
    display 
        - estimated price
            - quantity
            - type 
        - tab location
        - icon
        - remove icon


    on hover
        - show modal / tooltip of properties 
*/

import React, {Component} from "react"
import "./ValuableItem.css"

class ValuableItem extends Component {

    constructor(props) {
        super(props)

        this.state = {

        }
    }

    /* reformat as hover thingy if needed, idk how to do it tho ... */

    render() {
        return (
            <div className="valuable-item">
                <h3>item icon</h3>
                <div className="break" />
                <h3> est.price: 5 , C (chaos) </h3>
                <div className="break" />
                <button> remove </button>
            </div>
        )
    }

}

export default ValuableItem

