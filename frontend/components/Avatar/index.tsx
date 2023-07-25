import Image from "next/image";

interface AvatarProps {
  width: string;
  height: string;
}

export default function Avatar( props: AvatarProps) {
  const { width, height } = props;

  return (
    <div className={`rounded-full ${width} ${height}`} >
      <Image src ={`/default.png`} alt="Avatar Image" width={"96"} height={"96"} className="rounded-full" />
    </div>
  )
}