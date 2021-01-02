/*

- ha et icon
- en onclick event - Endre skjerm / få opp nå bilde / modal
- 

*/

import React, { Component } from "react"
import PropTypes from "prop-types"
import Button from "@material-ui/core/Button"

class HeaderIcon extends Component {

    constructor(props) {
        super(props)

        this.state = {
            //todo
        }

    }

    render() {
        return (
            <div className="headerIcon">
                <Button
                    variant="outlined"
                    size="small"
                    startIcon={this.props.icon}
                >
                    {this.props.text}
                </Button>
            </div>
        )
    }
}

HeaderIcon.propTypes = {
    text: PropTypes.string,
    icon: PropTypes.element
}

export default HeaderIcon