import { ConversationContext } from "@/context/ConversationContext";
import { Button, Input, Modal, Text } from "@nextui-org/react";
import React, { useState } from "react";
import LogOut from "../Auth/LogOut";
import Avatar from "../Avatar";
import ConversationList from "../ConversationList";
import Recent from "../MessageHandler/recent";

export default function SideBar() {
  const conversationsList = Recent();
  const [search, setSearch] = useState("");
  const filteredConversationsList = conversationsList.filter((conversation: any) => {
    return conversation.username.toLowerCase().includes(search.toLowerCase());
  });

  const [visible, setVisible] = React.useState(false);
  const { setConversation } = React.useContext(ConversationContext);
  const handler = () => setVisible(true);
  const closeHandler = () => {
    setVisible(false);
  };

  const [formValues, setFormValues] = useState({
    username: "",
  });

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormValues((prevFormValues) => ({
      ...prevFormValues,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    if (!formValues.username) return;
    if (formValues.username === localStorage.getItem("username")) return;
    if (conversationsList.find((conversation: any) => conversation.username === formValues.username)) return;
    const messageData = {
      username: formValues.username,
      messageHistory: []
    }
    setConversation(messageData);
    setVisible(false);
  };

  return (
    <div className="flex flex-col w-[480px] h-full bg-[#fbfaf5]" style={{ borderRight: "1px solid rgba(134,150,160,0.15)" }}>
      <div className="flex items-center justify-between w-full px-4">
        <div className="flex bg-[#fbfaf5] w-full h-14 py-3 items-center">
          <div className="flex cursor-pointer">
            <Avatar width="w-10" height="w-10" />
          </div>
        </div>
        <div className="flex gap-2">

          <div className="flex cursor-pointer w-10 h-10 items-center justify-center"
            onClick={handler}
          >
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" className="text-[#7828c8]">
              <path d="M21 2H3a2 2 0 00-2 2v14a2 2 0 002 2h5l4 4 4-4h5a2 2 0 002-2V4a2 2 0 00-2-2zM9 14H5v-2h4v2zm6 0h-4v-2h4v2zm4-5H5V7h14v2z" />
            </svg>

          </div>
          <Modal
            closeButton
            blur
            aria-labelledby="modal-title"
            open={visible}
            onClose={closeHandler}
            className="bg-[#fbfaf5]"
          >
            <Modal.Header>
              <Text id="modal-title" size={18}>
                Enter a new username
              </Text>
            </Modal.Header>
            <form onSubmit={() => handleSubmit}>
              <Modal.Body>
                <Input
                  name="username"
                  bordered
                  value={formValues.username}
                  fullWidth
                  onChange={handleChange}
                  color="primary"
                  size="lg"
                  placeholder="Enter Username"
                  required
                />
              </Modal.Body>
              <Modal.Footer>
                <Button auto flat onPress={closeHandler}>
                  Close
                </Button>
                <Button auto color="secondary" onPress={handleSubmit}>
                  Start Chat
                </Button>
              </Modal.Footer>
            </form>
          </Modal>

          <div className="flex cursor-pointer w-10 h-10 items-center justify-center"
            onClick={() => LogOut()}
          >
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" className="text-[#7828c8]">
              <circle cx="12" cy="12" r="10" />
              <path d="M15 9l-3 3 3 3" />
              <path d="M9 12H3" />
            </svg>
          </div>
        </div>
      </div>
      <div className="flex bg-[#fbfaf5] w-full h-max px-3 py-2">
        <div className="relative w-[95%] h-max">
          <div className="absolute text-[#7828c8] h-full w-9">
            <svg viewBox="0 0 24 24" width="24" height="24" className="left-[50%] right-[50%] ml-auto mr-auto h-full">
              <path fill="currentColor" d="M15.009 13.805h-.636l-.22-.219a5.184 5.184 0 0 0 1.256-3.386 5.207 5.207 0 1 0-5.207 5.208 5.183 5.183 0 0 0 3.385-1.255l.221.22v.635l4.004 3.999 1.194-1.195-3.997-4.007zm-4.808 0a3.605 3.605 0 1 1 0-7.21 3.605 3.605 0 0 1 0 7.21z">
              </path>
            </svg>
          </div>
          <div className="">
            <input className="w-[103%] h-9 rounded-lg bg-[#f4f3f0] text-black text-sm px-10" placeholder="Search recent chats..." value={search} onChange={e => setSearch(e.target.value)} />
          </div>
        </div>
      </div>
      <div className="flex flex-col w-full overflow-y-scroll" id="conversation">
        {
          filteredConversationsList.map((conversation, index) => {
            return (
              <ConversationList key={index} isFirstConversation={index == 0} data={conversation} />
            )
          })
        }
      </div>
    </div>
  )
}