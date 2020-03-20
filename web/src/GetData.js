/*
获取网易新闻
https://api.apiopen.top/getWangYiNews
 */


import React, {Component} from "react";
import Axios from "axios";
import News from "./News";

class GetData extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        Axios.get('https://api.apiopen.top/getWangYiNews').then(({data}) => {
            this.setState({
                data: data.result
            })
            // console.log(this.state.data)
            this.state.data.map((item, index) => {
                let title = item.title
                let path = item.path
                let image = item.image
                let passtime = item.passtime
                let po = {
                    title: title,
                    path: path,
                    image: image,
                    passtime: passtime,
                }
                Axios.post('news_saveUpdate', po)
            })
        })


    }

    render() {
        return (
            <div/>
        )
    }
}

export default GetData
