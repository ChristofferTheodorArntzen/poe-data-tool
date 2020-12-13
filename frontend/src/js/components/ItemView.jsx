import React, { Component } from 'react';
import { Card } from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';

class ItemView extends Component {

    constructor(props)
    {
        super(props);
        this.state = {
            response: null,
            stashTab: [],
            isLoaded: false,
            currenctStash: "0",
            currenctLeague: "Heist",
            realm: "pc",
            accountName: "Athzen",
            tabs: "0",
            tabIndex: "0",
            isShown: true
        };
        this.setIsShown = this.setIsShown.bind(this);
    }

    setIsShown(isShownValue) {
       this.setState(state => ({
           isShown: isShownValue
       }));
    }
        //?league=Standard&realm=pc&accountName=Athzen&tabs=0&tabIndex=4

    //     fetch('http://localhost:8080/private/stash-tab' + 
    //     '?=league=' + this.state.currenctLeague +
    //     '&realm=' + this.state.realm +
    //     'accountName=' + this.state.accountName  +
    //     'tabs=' + this.state.tabs +
    //     'tabIndex=' + this.state.tabIndex, {headers}
    // )

    componentDidMount() {
        const headers = {   'Conent-Type': 'application/json',
                            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64)',
                            'POESESSID': ''
                        }
        fetch('http://localhost:8080/private/stash-tab?league=Heist&realm=pc&accountName=Athzen&tabs=0&tabIndex=2', {headers}
            )
            .then(response => response.json())
            .then(data => this.setState({stashTab: data, isLoaded: true}));
    }  
    
    

    render() {

        const cards = []
        if(this.state.isLoaded) {
            const stashTabResponse = this.state.stashTab
            if(stashTabResponse != null)
            var items = []
            for (const [index, item] of stashTabResponse.items.entries()) {
                

                var implicitMod = null;

                var properties = []
                if (item.properties){
                    for (const [indexProperty, property] of item.properties.entries()) {
                        
                        if(property.implicitMod) {
                            implicitMod = property.implicitMod
                        }
                        
                        var propertyString = ""
                        if(property.name){
                            propertyString += property.name + " "
                        }
                        if(property.values && property.values.length > 0){
                            propertyString += property.values[0].slice(",0")[0]
                        }
    
                        properties.push(
                            <p>{propertyString}</p>
                        )
    
                    }                   
                }

                items.push(
                    <Card key={index} style={{ width: '12rem', backgroundColor: '#999', borderColor: '#FFF'}}>
                        <Card.Img variant="top" src={item.icon}  style={{ width: '4rem', height: '4rem', margin: 'auto'}}
                        onMouseEnter = {this.setIsShown(true)}
                        onMouseLeave = {this.setIsShown(false)}
                        >
                        </Card.Img>
                        { this.state.isShown && (
                            <Card.Body>
                            <Card.Title>{item.name}</Card.Title>
                        <Card.Text style={{ 'font-size': '10px', color: '#FFF'}}>{item.descrText}</Card.Text>
                        <Card.Text style={{ 'font-size': '10px', color: '#FFF'}}>{item.typeLine}</Card.Text>
                        <Card.Text style={{ 'font-size': '10px', color: '#FFF'}}>{item.implicitMod}</Card.Text>
                        {properties}
                        </Card.Body>
                        )}
                    </Card>
                )
            }
            
            cards.push(
                <div>
                    <hr style={{ borderTop: '3px solid #bbb'}} />
                    {items}
                </div>     
            )
        }
        
        return(
            <div style={{ display: 'inline-flex', flexWrap: 'wrap'}}>
              {cards}
            </div>
        )

        // const cards = []
        // if (this.state.isLoaded) {
        //     for (const [index, stashTab] of this.state.stashTabs.entries()) {
        //         if (stashTab && stashTab.items.length > 0) {
        //             var items = []
        //             for (const [index, item] of stashTab.items.entries()) {
        //                 items.push(
        //                     <Card key={index} style={{ width: '12rem', backgroundColor: '#999', borderColor: '#FFF'}}>
        //                         <Card.Img variant="top" src={item.icon}  style={{ width: '4rem', height: '4rem', margin: 'auto'}} />
        //                         <Card.Body>
        //                             <Card.Title>{item.name}</Card.Title>
        //                             <Card.Text style={{ 'font-size': '10px', color: '#FFF'}}>{item.descrText}</Card.Text>
        //                         </Card.Body>
        //                     </Card>
        //                 )
        //             }

        //             cards.push(
        //                 <div>
        //                     <hr style={{'border-top': '3px solid #bbb'}} />
        //                     <h1>{stashTab.stash}</h1>
        //                     {items}
        //                 </div>
        //             )
        //         }
        //     }
        // }
      
        //return (
        //  <div style={{ display: 'inline-flex', 'flexWrap': 'wrap'}}>
        //    {cards}
        //  </div>
        //)
    }
}
  
  export default ItemView;
