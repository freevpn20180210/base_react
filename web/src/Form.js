import React, {Component} from 'react';
import Axios from "axios";
import {message, DatePicker} from 'antd';
import 'antd/dist/antd.css';

/*
    新增组件
 */
class Form extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            title: '',
            path: '',
            image: '',
            passtime: '',
        }
    }

    //把输入的数据保存到state
    change = event => {
        const {name, value} = event.target
        this.setState({
            [name]: value
        })
    }

    //ant日期选择框被改变事件
    onChange = (value, dateString) => {
        this.setState({
            passtime: dateString
        })
    }

    //把已存入的数据提交，添加到表格
    submit = () => {
        Axios.post('news_saveUpdate', this.state).then((data) => {
            if (data.data.ok) {
                message.info('添加成功');
                this.props.query()
            }
        })
    }


    render() {
        const {id, title, path, image, passtime} = this.state;

        return (
            <form>
                <label htmlFor="title">标题</label>
                <input type="text" name='title' id='title' value={title} onChange={this.change}/>

                <label htmlFor="path">链接</label>
                <input type="text" name='path' id='path' value={path} onChange={this.change}/>

                <label htmlFor="image">图片地址</label>
                <input type="text" name='image' id='image' value={image} onChange={this.change}/>

                <label htmlFor="passtime">更新时间</label>
                <DatePicker showTime onChange={this.onChange}/>

                <input type="button" value="Submit" onClick={this.submit}/>
            </form>
        )
    }
}

export default Form
