import React, { Component } from "react"
import Button from "@material-ui/core/Button"
import NotificationsActiveIcon from '@material-ui/icons/NotificationsActive'

class TestAlertButton extends Component {


    render() {
        return (
            <div className="headerIcon">
                <Button
                    variant="outlined"
                    size="small"
                    startIcon={<NotificationsActiveIcon />}
                >
                    Test Alert
            </Button>
            </div>
        )
    }


}

export default TestAlertButton