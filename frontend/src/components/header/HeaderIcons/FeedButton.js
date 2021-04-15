import React from "react";
import Button from "@material-ui/core/Button";
import RssFeedIcon from '@material-ui/icons/RssFeed';
import { Link } from "react-router-dom";

const FeedButton = () => {
    return (
        <div className="headerIcon">
            <Button
                variant="outlined"
                size="small"
                startIcon={<RssFeedIcon />}
                component={Link}
                to={"/"}
            >
                Feed
				</Button>
        </div>
    );
}

export default FeedButton;